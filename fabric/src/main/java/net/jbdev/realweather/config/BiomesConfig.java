package net.jbdev.realweather.config;

import net.jbdev.realweather.biomes.BiomeType;

import java.util.HashMap;
import java.util.Map;

public class BiomesConfig {

    public Map<BiomeType, BiomeDto> biomes = new HashMap<>();

    public BiomesConfig addBiome(BiomeDto biome) {
        this.biomes.put(biome.type, biome);
        return this;
    }

}
