package net.jbdev.realweather;

import net.jbdev.realweather.players.IPlayerService;
import net.jbdev.realweather.players.PlayerService;
import net.jbdev.realweather.weather.IWeatherService;
import net.jbdev.realweather.weather.IWeatherServiceApi;
import net.jbdev.realweather.weather.WeatherService;
import net.jbdev.realweather.weather.WeatherServiceApi;

public class ServiceManager {
    public final IRealWeatherEffects realWeatherEffects;
    public final IPlayerService playerService;
    public final IWeatherService weatherService;
    public final IWeatherServiceApi weatherServiceApi;

    public ServiceManager() {
        this.realWeatherEffects = new RealWeatherEffectsJson(new RealWeatherEffects());
        this.playerService = new PlayerService();
        this.weatherServiceApi = new WeatherServiceApi();
        this.weatherService = new WeatherService(this.realWeatherEffects);
    }

    public void init() {
        this.weatherServiceApi.init();
        this.realWeatherEffects.init();
        this.playerService.init();
        this.weatherService.init();
    }
}
