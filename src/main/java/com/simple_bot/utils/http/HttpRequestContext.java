package com.simple_bot.utils.http;

import com.simple_bot.app.AppContext;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;

/**
 * @author Иван
 * @version $Id$
 */
public class HttpRequestContext {
    private String url;
    private String cookies;
    private List<NameValuePair> params;
    private Map<String, String> headers;
    private boolean log;

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

    public List<NameValuePair> getParams() {
        return params;
    }

    public void setParams(List<NameValuePair> params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    public static class Builder {
        private String url;
        private String cookies;
        private List<NameValuePair> params;
        private Map<String, String> headers;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setCookies(String cookies) {
            this.cookies = cookies;
            return this;
        }

        public Builder setParams(List<NameValuePair> params) {
            this.params = params;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public HttpRequestContext build() {
            HttpRequestContext context = new HttpRequestContext();
            context.setCookies(cookies);
            context.setHeaders(headers);
            context.setParams(params);
            context.setUrl(url);
            context.setLog(Boolean.valueOf(AppContext.getProperty("http.logging")));
            return context;
        }
    }
}
