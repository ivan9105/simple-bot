package com.simple_bot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author ivan-melnikov
 */
public class PropertiesUtils {
    public static final String HEADER = "header";
    public static final String HTTP = "http";

    public static Map<String, String> loadHeaderProperties() {
        return loadMap(HEADER + ".properties");
    }

    public static Map<String, String> loadHttpProperties() {
        return loadMap(HTTP + ".properties");
    }

    private static Map<String, String> loadMap(String path) {
        Properties properties = new Properties();

        ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
        try {
            InputStream input = classLoader.getResourceAsStream(path);
            properties.load(input);
        } catch (IOException ignore) {
        }
        return properties.stringPropertyNames().stream().collect(Collectors.toMap(
                name -> name,
                properties::getProperty));
    }
}
