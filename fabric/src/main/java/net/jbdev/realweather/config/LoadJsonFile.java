package net.jbdev.realweather.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class LoadJsonFile {
    public static JsonObject loadFile(Path filePath) {
        try {
            StringBuilder builder = new StringBuilder();
            Scanner scanner = new Scanner(filePath);

            while(scanner.hasNextLine()){
                //process each line
                String line = scanner.nextLine();
                builder.append(line);
            }
            scanner.close();

            return JsonParser.parseString(builder.toString()).getAsJsonObject();
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
