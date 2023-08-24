package net.jbdev.realweather.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class ResourceFileHelper {
    public static final int DEFAULT_BUFFER_SIZE = 8192;


    public static void copy(String resourceFilename, Path destinationPath) {
        InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(resourceFilename);
        try (FileOutputStream outputStream = new FileOutputStream(destinationPath.toFile(), false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
