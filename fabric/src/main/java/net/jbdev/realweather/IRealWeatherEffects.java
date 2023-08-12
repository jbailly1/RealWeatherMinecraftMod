package net.jbdev.realweather;

public interface IRealWeatherEffects {
    boolean isEnabled();
    boolean isWindEnabled();
    boolean isTemperatureEnabled();
    void enable(boolean enable);
    void enableWind(boolean enable);
    void enableTemperature(boolean enable);
    void enableAllEffects(boolean enable);
    String info();
    void init();
}
