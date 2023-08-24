package net.jbdev.realweather.weather;

import java.util.HashMap;
import java.util.Map;

public class HourlyDto {
    public static final int HOURS_PER_DAY = 24;
    public Map<String, float[]> floatingParams = new HashMap<>();
    public Map<String, String[]> stringParams = new HashMap<>();
    public Map<String, int[]> decimalParams = new HashMap<>();

    public void addOrUpdateParam(String paramName, int valueIndex, float value) {
        if (!this.floatingParams.containsKey(paramName)) {
            this.floatingParams.put(paramName, new float[this.HOURS_PER_DAY]);
        }

        this.floatingParams.get(paramName)[valueIndex] = value;
    }

    public void addOrUpdateParam(String paramName, int valueIndex, int value) {
        if (!this.decimalParams.containsKey(paramName)) {
            this.decimalParams.put(paramName, new int[this.HOURS_PER_DAY]);
        }

        this.decimalParams.get(paramName)[valueIndex] = value;
    }

    public void addOrUpdateParam(String paramName, int valueIndex, String value) {
        if (!this.stringParams.containsKey(paramName)) {
            this.stringParams.put(paramName, new String[this.HOURS_PER_DAY]);
        }

        this.stringParams.get(paramName)[valueIndex] = value;
    }
}
