package net.jbdev.realweather.weather;

import net.jbdev.realweather.biomes.BiomeType;

import java.util.Map;

public interface IWeatherServiceApi {
    Map<BiomeType, WeatherDto> fetchAllBiomesTodayWeather();
}
