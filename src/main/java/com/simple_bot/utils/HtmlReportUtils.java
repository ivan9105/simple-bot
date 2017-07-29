package com.simple_bot.utils;

import com.simple_bot.dto.BaseResponse;
import com.simple_bot.utils.http.HttpRequestContext;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

/**
 * @author ivan-melnikov
 */
public class HtmlReportUtils {
    private static final String TEMPLATE_PATH = "template" + separator + "html_report_template.ftl";
    private static final String BOOTSTRAP_CSS_PATH = "template" + separator + "css" + separator + "bootstrap.min.css";
    private static final String PRISM_CSS_PATH = "template" + separator + "css" + separator + "prism.css";
    private static final String PRISM_JS_PATH = "template" + separator + "js" + separator + "prism.js";

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

    public static void buildReport(String folder) throws IOException {
        Map<HttpRequestContext, BaseResponse> requestData = getRequestData();

        Configuration configuration = new Configuration();
        File templateFile = getFile(TEMPLATE_PATH);
        File bootstrapCssFile = getFile(BOOTSTRAP_CSS_PATH);
        File prismCssFile = getFile(PRISM_CSS_PATH);
        File prismJsFile = getFile(PRISM_JS_PATH);


        configuration.setDirectoryForTemplateLoading(templateFile);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //Todo
    }

    private static File getFile(String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            throw new RuntimeException(String.format("File %s not found", file.getPath()));
        }
        return file;
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
