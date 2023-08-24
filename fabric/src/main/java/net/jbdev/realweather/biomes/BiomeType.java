package net.jbdev.realweather.biomes;

public enum BiomeType {
    TAIGA("TAIGA"),
    JUNGLE("JUNGLE"),
    MESA("MESA"),
    PLAINS("PLAINS"),
    SAVANNA("SAVANNA"),
    BEACH("BEACH"),
    FOREST("FOREST"),
    OCEAN("OCEAN"),
    DESERT("DESERT"),
    RIVER("RIVER"),
    SWAMP("SWAMP"),
    MUSHROOM("MUSHROOM"),
    MOUNTAIN("MOUNTAIN"),
    BADLANDS("BADLANDS"),
    DEEP_OCEAN("DEEP_OCEAN");

    private String name;

    BiomeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
