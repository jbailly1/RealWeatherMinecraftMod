package net.jbdev.realweather.events;

import net.jbdev.realweather.players.IPlayerService;
import net.jbdev.realweather.weather.IWeatherService;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;

import java.util.Objects;

public class ServerEndWorldTickHandler {
    private final IPlayerService playerService;
    private final IWeatherService weatherService;
    private final Logger logger;
    private static final int MAX_TICKS = 65536;
    private static final int TICK_PER_HOUR = MAX_TICKS / 100;

    public ServerEndWorldTickHandler(IPlayerService playerService, IWeatherService weatherService, Logger logger) {
        this.playerService = playerService;
        this.weatherService = weatherService;
        this.logger = logger;
    }

    public void handleWorldTick(ServerWorld world) {
        if (!this.weatherService.isEnabled()) return;
        this.weatherService.update(world);
        if (world.getTimeOfDay() % TICK_PER_HOUR == 0) {
            for (ServerPlayerEntity connectedPlayer : playerService.getAll()) {
                PlayerEntity foundPlayer = world.getPlayerByUuid(connectedPlayer.getUuid());
                if (foundPlayer == null) {
                    continue;
                }
                BlockPos playerPosition = foundPlayer.getBlockPos();
                weatherService.applyPlayerWeather(world.getBiome(playerPosition).value(), foundPlayer);
                foundPlayer.increaseTravelMotionStats(2, 0, 2);
                float speedMultiplier = 0.5f; // Change this to your desired value
                Objects.requireNonNull(foundPlayer.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED))
                        .setBaseValue(0.1 * speedMultiplier);
                this.logger.info(String.format("Player movement speed %s", foundPlayer.getMovementSpeed()));
                //foundPlayer.sendMessage(Text.of(String.format("We change your speed %s", foundPlayer.speed)), true);
            }
        }
    }
}
