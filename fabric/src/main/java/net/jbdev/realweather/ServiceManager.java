package net.jbdev.realweather;
import net.jbdev.realweather.weather.IWeatherService;
import net.jbdev.realweather.weather.WeatherService;
public class ServiceManager {
    public final IRealWeatherEffects realWeatherEffects;
    public final IWeatherService weatherService;

    public ServiceManager() {
        this.realWeatherEffects = new RealWeatherEffectsJson(new RealWeatherEffects());
        this.weatherService = new WeatherService(this.realWeatherEffects);
    }

    public void init() {
        this.realWeatherEffects.init();
        this.weatherService.init();
    }
}
