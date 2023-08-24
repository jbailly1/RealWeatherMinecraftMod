package net.jbdev.realweather.config;

import net.jbdev.realweather.biomes.BiomeType;

public class BiomeDto {
    public BiomeType type;
    public double Latitude;
    public double Longitude;

    public BiomeDto(BiomeType type, double latitude, double longitude) {
        this.type = type;
        this.Latitude = latitude;
        this.Longitude = longitude;
    }
}
