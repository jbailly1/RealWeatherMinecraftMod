package net.jbdev.realweather;

public class RealWeatherEffects implements IRealWeatherEffects {
    private boolean enable;
    private boolean temperatureEffect;
    private boolean windEffect;

    public RealWeatherEffects() {
        this.enable = false;
        this.temperatureEffect = false;
        this.windEffect = false;
    }

    public void enableAllEffects(boolean enable) {
        this.windEffect = enable;
        this.temperatureEffect = enable;
    }

    @Override
    public String info() {
        return String.format("""
                    Real weather effect info:
                     - enable: %b
                     - effect.temp: %b
                     - effect.wind: %b""",
                this.enable, this.temperatureEffect, this.windEffect);
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public boolean isWindEnabled() {
        return this.windEffect;
    }

    @Override
    public boolean isTemperatureEnabled() {
        return this.temperatureEffect;
    }

    @Override
    public void enable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void enableWind(boolean enable) {
        this.windEffect = enable;
    }

    @Override
    public void enableTemperature(boolean enable) {
        this.temperatureEffect = enable;
    }
}
