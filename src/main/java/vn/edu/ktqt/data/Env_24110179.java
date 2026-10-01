package vn.edu.ktqt.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Env_24110179 {
    private static final Map<String, String> VALUES = new HashMap<>();

    static {
        Path path = Paths.get(System.getProperty("user.dir"), ".env");
        if (!Files.exists(path)) {
            path = Paths.get(System.getProperty("catalina.base", ""), ".env");
        }
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int index = line.indexOf('=');
                    if (index > 0 && !line.trim().startsWith("#")) {
                        VALUES.put(line.substring(0, index).trim(), line.substring(index + 1).trim());
                    }
                }
            } catch (IOException ignored) {
            }
        }
    }

    private Env_24110179() {
    }

    public static String get(String key) {
        String value = System.getenv(key);
        return value != null ? value : VALUES.get(key);
    }
}
