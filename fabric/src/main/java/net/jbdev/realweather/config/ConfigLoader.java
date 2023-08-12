package net.jbdev.realweather.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.jbdev.realweather.ConfigDto;
import net.jbdev.realweather.RealWeatherMod;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ConfigLoader {
    private static final String filename = String.format("%s.json", RealWeatherMod.MOD_ID);
    private static final Path filenamePath = FabricLoader
            .getInstance()
            .getConfigDir()
            .resolve(filename);

    public static ConfigDto load() {
        try {
            StringBuilder builder = new StringBuilder();
            Scanner scanner = new Scanner(filenamePath);

            while(scanner.hasNextLine()){
                //process each line
                String line = scanner.nextLine();
                builder.append(line);
            }
            scanner.close();

            return jsonToDto(JsonParser.parseString(builder.toString()).getAsJsonObject());
        } catch (FileNotFoundException e) {
            ConfigDto defaultConfig = new ConfigDto();
            save(defaultConfig);
            return load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
