package net.jbdev.realweather.weather;

import java.util.Arrays;
import java.util.List;

public enum WeatherParameter {
    WIND_SPEED_10("windspeed_10m"),
    WIND_SPEED_80("windspeed_80m"),
    WIND_SPEED_120("windspeed_120m"),
    WIND_SPEED_180("windspeed_180m"),
    WIND_DIRECTION_10("winddirection_10m"),
    WIND_DIRECTION_80("winddirection_80m"),
    WIND_DIRECTION_120("winddirection_120m"),
    WIND_DIRECTION_180("winddirection_180m"),
    TEMPERATURE_2("temperature_2m"),
    TEMPERATURE_80("temperature_80m"),
    TEMPERATURE_120("temperature_120m"),
    TEMPERATURE_180("temperature_180m"),
    TIME("time");

    private String name;

    WeatherParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static List<String> getFloatingParams() {
        return Arrays.stream(WeatherParameter.values())
                .filter(params -> !params.name.contains("winddirection") && !params.name.contains("time"))
                .map(params -> params.name).toList();
    }

    public static List<String> getDecimalParams() {
        return Arrays.stream(WeatherParameter.values())
                .filter(params -> params.name.contains("winddirection") && !params.name.contains("time"))
                .map(params -> params.name).toList();
    }

    public static List<String> getStringParams() {
        return Arrays.stream(WeatherParameter.values())
                .filter(params -> params.name.contains("time"))
                .map(params -> params.name).toList();
    }

    public static List<String> getAllParams() {
        return Arrays.stream(WeatherParameter.values())
                .map(params -> params.name).toList();
    }

    public static List<String> getAllSearchParams() {
        return Arrays.stream(WeatherParameter.values())
                .filter(param -> !param.name.equals("time"))
                .map(params -> params.name).toList();
    }
}
