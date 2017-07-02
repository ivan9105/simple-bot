package com.simple_bot.app;

import com.simple_bot.utils.PropertiesUtils;

import javax.annotation.Nullable;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Иван
 * @version $Id$
 */
public class AppContext {
    private static Map<String, String> propertiesMap = new ConcurrentHashMap<>();

    public static void startContext() {
        CookieHandler.setDefault(new CookieManager());
        propertiesMap.putAll(PropertiesUtils.loadHeaderProperties());
    }

    @Nullable
    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }
}
