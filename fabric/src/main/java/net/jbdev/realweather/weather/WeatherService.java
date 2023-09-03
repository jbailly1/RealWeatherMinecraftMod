package net.jbdev.realweather.weather;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.jbdev.realweather.IRealWeatherEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;

import static net.jbdev.realweather.RealWeatherMod.MOD_ID;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class WeatherService implements IWeatherService {

    private final IRealWeatherEffects weatherEffects;
    private final IWeatherServiceApi weatherServiceApi;
    private final Logger logger;

    public WeatherService(IRealWeatherEffects weatherEffects, IWeatherServiceApi weatherServiceApi, Logger logger) {
        this.weatherEffects = weatherEffects;
        this.weatherServiceApi = weatherServiceApi;
        this.logger = logger;
    }

    @Override
    public void applyPlayerWeather(Biome biome, PlayerEntity playerEntity) {

    }

    @Override
    public void update(ServerWorld world) {
        //logger.info(String.format("World time %d", world.getTimeOfDay()));
    }

    @Override
    public void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(
                literal(MOD_ID)
                    .requires(cs -> cs.hasPermissionLevel(0))
                    .then(literal("enable")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(
                                    argument("enabled", BoolArgumentType.bool())
                                            .executes(context -> {
                                                this.weatherEffects.enable(BoolArgumentType.getBool(context, "enabled"));
                                                context.getSource().sendMessage(Text.literal(String.format("Real Weather mod %s effect", this.weatherEffects.isEnabled() ? "enable" : "disabled")));
                                                return 1;
                                            })
                            )
                    )
                    .then(literal("effect.wind")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(
                                    argument("enabled", BoolArgumentType.bool())
                                            .executes(context -> {
                                                this.weatherEffects.enableWind(BoolArgumentType.getBool(context, "enabled"));
                                                context.getSource().sendMessage(Text.literal(String.format("Real Weather wind effect %s", this.weatherEffects.isWindEnabled() ? "enable" : "disabled")));
                                                return 1;
                                            })
                            )
                    )
                    .then(literal("effect.temp")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(
                                    argument("enabled", BoolArgumentType.bool())
                                            .executes(context -> {
                                                this.weatherEffects.enableTemperature(BoolArgumentType.getBool(context, "enabled"));
                                                context.getSource().sendMessage(Text.literal(String.format("Real Weather temperature effect %s", this.weatherEffects.isTemperatureEnabled() ? "enable" : "disabled")));
                                                return 1;
                                            })
                            )
                    )
                    .then(literal("effect.all")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(
                                    argument("enabled", BoolArgumentType.bool())
                                            .executes(context -> {
                                                this.weatherEffects.enableAllEffects(BoolArgumentType.getBool(context, "enabled"));
                                                context.getSource().sendMessage(Text.literal(this.weatherEffects.info()));
                                                return 1;
                                            })
                            )
                    )
                    .executes(context -> {
                        context.getSource().sendMessage(Text.literal(this.weatherEffects.info()));
                        return 1;
                    })
            )
        );
    }

    @Override
    public boolean isEnabled() {
        return this.weatherEffects.isEnabled();
    }
}
