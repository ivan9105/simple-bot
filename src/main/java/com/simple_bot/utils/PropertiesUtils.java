package com.simple_bot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Иван
 * @version $Id$
 */
public class PropertiesUtils {
    public static final String HEADER = "header";

    public static Map<String, String> loadHeaderProperties() {
        Properties properties = new Properties();

        ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
        try {
            InputStream input = classLoader.getResourceAsStream(HEADER + ".properties");
            properties.load(input);
        } catch (IOException ignore) {
        }
        return properties.stringPropertyNames().stream().collect(Collectors.toMap(
                name -> name,
                properties::getProperty));
    }
}
