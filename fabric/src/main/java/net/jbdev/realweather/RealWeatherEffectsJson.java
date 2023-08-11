package net.jbdev.realweather;

public class RealWeatherEffectsJson implements IRealWeatherEffects {

    private final IRealWeatherEffects weatherEffects;

    public RealWeatherEffectsJson(IRealWeatherEffects weatherEffects) {
        this.weatherEffects = weatherEffects;
    }

    @Override
    public boolean isEnabled() {
        return weatherEffects.isEnabled();
    }

    @Override
    public boolean isWindEnabled() {
        return weatherEffects.isWindEnabled();
    }

    @Override
    public boolean isTemperatureEnabled() {
        return weatherEffects.isTemperatureEnabled();
    }

    @Override
    public void enable(boolean enable) {
        weatherEffects.enable(enable);
        //TODO write in config
    }

    @Override
    public void enableWind(boolean enable) {
        weatherEffects.enableWind(enable);
    }

    @Override
    public void enableTemperature(boolean enable) {
        weatherEffects.enableTemperature(enable);
    }

    @Override
    public void enableAllEffects(boolean enable) {
        weatherEffects.enableAllEffects(enable);
    }

    @Override
    public String info() {
        return weatherEffects.info();
    }
}
