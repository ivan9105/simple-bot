package com.simple_bot.utils.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchContext {
    //tagPath values
    private Map<String, String> pathMap = new HashMap<>();
    //tagId, tagLocalization
    private Map<String, String> columnMap = new HashMap<>();
    //tagLocalization, values
    private Map<String, List<String>> dataMap = new HashMap<>();

    public Map<String, String> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, String> columnMap) {
        this.columnMap = columnMap;
    }

    public Map<String, List<String>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, List<String>> dataMap) {
        this.dataMap = dataMap;
    }
}
