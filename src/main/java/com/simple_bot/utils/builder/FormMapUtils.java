package com.simple_bot.utils.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Иван
 * @version $Id$
 */
public class FormMapUtils {
    private FormMapUtils() {
    }

    public static Map<String, String> of(String paramName, String paramValue) {
        Map<String, String> map = new HashMap<>();
        map.put(paramName, paramValue);
        return map;
    }

    public static Map<String, String> of(String paramName1, String paramValue1,
                                         String paramName2, String paramValue2) {
        Map<String, String> map = new HashMap<>();
        map.put(paramName1, paramValue1);
        map.put(paramName2, paramValue2);
        return map;
    }

    public static Map<String, String> of(String paramName1, String paramValue1,
                                         String paramName2, String paramValue2,
                                         String paramName3, String paramValue3) {
        Map<String, String> map = new HashMap<>();
        map.put(paramName1, paramValue1);
        map.put(paramName2, paramValue2);
        map.put(paramName3, paramValue3);
        return map;
    }

    public static Map<String, String> of(String paramName1, String paramValue1,
                                         String paramName2, String paramValue2,
                                         String paramName3, String paramValue3,
                                         String paramName4, String paramValue4) {
        Map<String, String> map = new HashMap<>();
        map.put(paramName1, paramValue1);
        map.put(paramName2, paramValue2);
        map.put(paramName3, paramValue3);
        map.put(paramName4, paramValue4);
        return map;
    }

    public static Map<String, String> of(String paramName1, String paramValue1,
                                         String paramName2, String paramValue2,
                                         String paramName3, String paramValue3,
                                         String paramName4, String paramValue4,
                                         String paramName5, String paramValue5) {
        Map<String, String> map = new HashMap<>();
        map.put(paramName1, paramValue1);
        map.put(paramName2, paramValue2);
        map.put(paramName3, paramValue3);
        map.put(paramName4, paramValue4);
        map.put(paramName5, paramValue5);
        return map;
    }
}
