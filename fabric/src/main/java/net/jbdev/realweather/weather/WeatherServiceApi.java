package net.jbdev.realweather.weather;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.jbdev.realweather.biomes.BiomeType;
import net.jbdev.realweather.config.BiomeDto;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherServiceApi implements IWeatherServiceApi {
    private final List<String> floatingParams = WeatherParameter.getFloatingParams();
    private final List<String> decimalParams = WeatherParameter.getDecimalParams();
    private final List<String> stringParams = WeatherParameter.getStringParams();

    @Override
    public Map<BiomeType, WeatherDto> fetchAllBiomesTodayWeather() {
        var weatherPerBiomes = new HashMap<BiomeType, WeatherDto>();

        try {
            weatherPerBiomes.put(BiomeType.PLAINS, this.fetchWeather(new BiomeDto(BiomeType.PLAINS, 47.4602, -114.8829)));
        } catch (IOException ignored) {

        }

        return weatherPerBiomes;
    }

    private WeatherDto fetchWeather(BiomeDto config) throws IOException {
        InputStream is = new URL(this.createWeatherUrl(config)).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JsonObject json = JsonParser.parseString(jsonText).getAsJsonObject();
            return this.jsonToWeatherDto(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            is.close();
        }
    }

    private String createWeatherUrl(BiomeDto config) {
        String otherParams = String.format("hourly=%s&forecast_days=1", String.join(",", WeatherParameter.getAllSearchParams()));
        return String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&%s", config.Latitude, config.Longitude, otherParams);
    }

    private WeatherDto jsonToWeatherDto(JsonObject weatherJson) {
        var hourlyResult = weatherJson.getAsJsonObject("hourly");
        var weather = new WeatherDto();

        for (String param : WeatherParameter.getAllParams()) {
            var jsonValues = hourlyResult.getAsJsonArray(param);
            for (int i = 0; i < HourlyDto.HOURS_PER_DAY; i++) {
                if (this.isParamFloatingValue(param)) {
                    weather.HourlyData.addOrUpdateParam(param, i, jsonValues.get(i).getAsFloat());
                } else if (this.isParamDecimalValue(param)) {
                    weather.HourlyData.addOrUpdateParam(param, i, jsonValues.get(i).getAsInt());
                } else if (this.isParamStringValue(param)) {
                    weather.HourlyData.addOrUpdateParam(param, i, jsonValues.get(i).getAsString());
                }
            }
        }

        return weather;
    }

    private boolean isParamFloatingValue(String parameterName) {
        return this.floatingParams.contains(parameterName);
    }

    private boolean isParamDecimalValue(String parameterName) {
        return this.decimalParams.contains(parameterName);
    }

    private boolean isParamStringValue(String parameterName) {
        return this.stringParams.contains(parameterName);
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
