package net.jbdev.realweather.weather;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.biome.Biome;

public interface IWeatherService {
    void applyPlayerWeather(Biome biome, PlayerEntity playerEntity);
    void update(ServerWorld world);
    void init();
    boolean isEnabled();
}
