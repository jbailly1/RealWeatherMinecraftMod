package net.jbdev.realweather.config;

import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.jbdev.realweather.ConfigDto;
import net.jbdev.realweather.RealWeatherMod;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Objects;

public class ConfigLoader {
    private static final String filename = String.format("%s.json", RealWeatherMod.MOD_ID);
    private static final Path filenamePath = FabricLoader
            .getInstance()
            .getConfigDir()
            .resolve(filename);


    public static ConfigDto load() {
        if (!filenamePath.toFile().exists()) {
            ResourceFileHelper.copy("assets/realweather/defaults.json", filenamePath);
        }

        return jsonToDto(Objects.requireNonNull(LoadJsonFile.loadFile(filenamePath)));
    }


    private static ConfigDto jsonToDto(JsonObject jsonObject) {
        ConfigDto configDto = new ConfigDto();
        configDto.Enabled = jsonObject.get("enable").getAsBoolean();

        JsonObject effects = jsonObject.getAsJsonObject("effects");

        configDto.WindEnabled = effects.get("wind").getAsBoolean();
        configDto.TemperatureEnabled = effects.get("temperature").getAsBoolean();

        return configDto;
    }

    private static JsonObject dtoToJson(ConfigDto configDto) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("enable", configDto.Enabled);

        JsonObject effects = new JsonObject();

        effects.addProperty("wind", configDto.WindEnabled);
        effects.addProperty("temperature", configDto.TemperatureEnabled);

        jsonObject.add("effects", effects);

        return jsonObject;
    }

    public static void save(ConfigDto configDto) {
        try {
            FileWriter fileWriter = new FileWriter(filenamePath.toFile());
            fileWriter.write(dtoToJson(configDto).toString());
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
