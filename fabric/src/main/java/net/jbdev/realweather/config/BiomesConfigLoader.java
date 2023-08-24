package net.jbdev.realweather.config;

import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.jbdev.realweather.RealWeatherMod;
import net.jbdev.realweather.biomes.BiomeType;

import java.nio.file.Path;
import java.util.Objects;

public class BiomesConfigLoader {
    private static final String filename = String.format("%s-biomes.json", RealWeatherMod.MOD_ID);
    private static final Path filenamePath = FabricLoader
            .getInstance()
            .getConfigDir()
            .resolve(filename);

    public static BiomesConfig load() {
        if (!filenamePath.toFile().exists()) {
            ResourceFileHelper.copy("assets/realweather/default-biomes-config.json", filenamePath);
        }

        return jsonToDto(Objects.requireNonNull(LoadJsonFile.loadFile(filenamePath)));
    }

    private static BiomesConfig jsonToDto(JsonObject loadConfig) {
        var biomesConfig = new BiomesConfig();

        var biomes = loadConfig.getAsJsonArray("biomes");
        for (var value : biomes) {
            var valueAsObject = value.getAsJsonObject();
            var biomeDto = new BiomeDto(BiomeType.valueOf(valueAsObject.get("type").getAsString()),
                    valueAsObject.get("latitude").getAsDouble(),
                    valueAsObject.get("longitude").getAsDouble());
            biomesConfig.addBiome(biomeDto);
        }

        return biomesConfig;
    }
}
