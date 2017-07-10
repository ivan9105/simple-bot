package com.simple_bot.utils;

import com.simple_bot.dto.BaseResponse;
import com.simple_bot.utils.http.HttpRequestContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Иван
 * @version $Id$
 */
public class HtmlReportUtils {
    static ThreadLocal<RequestProfiler> profiler = new ThreadLocal<RequestProfiler>() {
        @Override
        protected RequestProfiler initialValue() {
            return new RequestProfiler();
        }
    };

    public static void request(HttpRequestContext context, BaseResponse response) {
        profiler().request(context, response);
    }

    public static Map<HttpRequestContext, BaseResponse> getRequestData() {
        return profiler().getRequestData();
    }

    public static void buildReport(String folder) {
        //Todo
    }

    public static void clear() {
        profiler().clear();
    }

    private static RequestProfiler profiler() {
        return profiler.get();
    }

    static class RequestProfiler {
        Map<HttpRequestContext, BaseResponse> requestData = new HashMap<>();

        public void request(HttpRequestContext context, BaseResponse response) {
            requestData.put(context, response);
        }

        public Map<HttpRequestContext, BaseResponse> getRequestData() {
            return requestData;
        }

        public void clear() {
            requestData.clear();
        }
    }
}
