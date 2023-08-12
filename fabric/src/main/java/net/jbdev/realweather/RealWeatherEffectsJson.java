package net.jbdev.realweather;

import net.jbdev.realweather.config.ConfigLoader;

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
        this.save();
    }

    @Override
    public void enableWind(boolean enable) {
        weatherEffects.enableWind(enable);
        this.save();
    }

    @Override
    public void enableTemperature(boolean enable) {
        weatherEffects.enableTemperature(enable);
        this.save();
    }

    @Override
    public void enableAllEffects(boolean enable) {
        weatherEffects.enableAllEffects(enable);
        this.save();
    }

    @Override
    public String info() {
        return weatherEffects.info();
    }

    @Override
    public void init() {
        ConfigDto loadedConfig = ConfigLoader.load();
        this.setFromDto(loadedConfig);
    }

    private ConfigDto convertToDto() {
        ConfigDto configDto = new ConfigDto();
        configDto.Enabled = this.isEnabled();
        configDto.WindEnabled = this.isWindEnabled();
        configDto.TemperatureEnabled = this.isTemperatureEnabled();

        return configDto;
    }

    private void setFromDto(ConfigDto config) {
        this.enable(config.Enabled);
        this.enableWind(config.WindEnabled);
        this.enableTemperature(config.TemperatureEnabled);
    }

    private void save() {
        ConfigLoader.save(this.convertToDto());
    }
}
