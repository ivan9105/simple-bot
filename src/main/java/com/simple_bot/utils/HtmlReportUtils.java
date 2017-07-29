package com.simple_bot.utils;

import com.simple_bot.dto.BaseResponse;
import com.simple_bot.utils.http.HttpRequestContext;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.http.NameValuePair;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.io.File.separator;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.collections4.MapUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author ivan-melnikov
 */
public class HtmlReportUtils {
    private static final String TEMPLATE_PATH = "template" + separator + "html_report_template.ftl";
    private static final String BOOTSTRAP_CSS_PATH = "template" + separator + "css" + separator + "bootstrap.min.css";
    private static final String PRISM_CSS_PATH = "template" + separator + "css" + separator + "prism.css";
    private static final String PRISM_JS_PATH = "template" + separator + "js" + separator + "prism.js";

    private static final String BOOTSTRAP_CSS_PARAM = "bootstrapCss";
    private static final String PRISM_CSS_PARAM = "prismCss";
    private static final String PRISM_JS_PARAM = "prismJs";

    private static final String FILE_PREFIX = "file://";

    private static ThreadLocal<RequestProfiler> profiler = ThreadLocal.withInitial(RequestProfiler::new);

    public static void request(HttpRequestContext context, BaseResponse response) {
        profiler().request(context, response);
    }

    private static Map<HttpRequestContext, BaseResponse> getRequestData() {
        return profiler().getRequestData();
    }

    public static void buildReport(String folder) throws IOException {
        Configuration configuration = new Configuration();
        File templateFile = getFile(TEMPLATE_PATH);

        configuration.setDirectoryForTemplateLoading(templateFile);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> params = new HashMap<>();
        params.put(BOOTSTRAP_CSS_PARAM, FILE_PREFIX + getFile(BOOTSTRAP_CSS_PATH).getAbsolutePath());
        params.put(PRISM_CSS_PARAM, FILE_PREFIX + getFile(PRISM_CSS_PATH).getAbsolutePath());
        params.put(PRISM_JS_PARAM, FILE_PREFIX + getFile(PRISM_JS_PATH).getAbsolutePath());

        Set<Step> steps = getRequestData().entrySet().stream().map(entry -> {
            HttpRequestContext context = entry.getKey();
            BaseResponse response = entry.getValue();

            return new Step(
                    new Step.Request(
                            isNotEmpty(context.getParams()) ? "POST::" + context.getUrl() : "GET::" + context.getUrl(),
                            context.getCookies(),
                            isNotEmpty(context.getHeaders()) ? toHeaderStr(context.getHeaders()) : EMPTY,
                            isNotEmpty(context.getParams()) ? toParams(context.getParams()) : Collections.emptyList()),
                    new Step.Response(
                            response.getCode(),
                            isNotEmpty(response.getHeaders()) ? toHeaderStr(response.getHeaders()) : EMPTY,
                            isNotEmpty(response.getCookies())
                                    ? response.getCookies().stream().collect(Collectors.joining(", ")) : EMPTY,
                            response.getContent()));
        }).collect(Collectors.toSet());

        //Todo
    }

    private static List<Step.Request.Param> toParams(Set<NameValuePair> pairs) {
        //Todo
    }

    private static String toHeaderStr(Map<String, String> headerMap) {
        return headerMap.entrySet().stream()
                .map(entry -> String.format("%s: %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
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

        void request(HttpRequestContext context, BaseResponse response) {
            requestData.put(context, response);
        }

        Map<HttpRequestContext, BaseResponse> getRequestData() {
            return requestData;
        }

        void clear() {
            requestData.clear();
        }
    }

    static class Step implements Serializable {
        private Request request;
        private Response response;

        public Step() {
        }

        public Step(Request request, Response response) {
            this.request = request;
            this.response = response;
        }

        static class Response implements Serializable {
            private Integer status;
            private String header;
            private String cookies;
            private String content;

            public Response() {
            }

            public Response(Integer status, String header, String cookies, String content) {
                this.status = status;
                this.header = header;
                this.cookies = cookies;
                this.content = content;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public String getCookies() {
                return cookies;
            }

            public void setCookies(String cookies) {
                this.cookies = cookies;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        static class Request implements Serializable {
            private String url;
            private String cookies;
            private String header;
            private List<Param> params;

            public Request() {
            }

            public Request(String url, String cookies, String header, List<Param> params) {
                this.url = url;
                this.cookies = cookies;
                this.header = header;
                this.params = params;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCookies() {
                return cookies;
            }

            public void setCookies(String cookies) {
                this.cookies = cookies;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public List<Param> getParams() {
                return params;
            }

            public void setParams(List<Param> params) {
                this.params = params;
            }

            static class Param implements Serializable {
                private String name;
                private String value;

                public Param() {
                }

                public Param(String name, String value) {
                    this.name = name;
                    this.value = value;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
