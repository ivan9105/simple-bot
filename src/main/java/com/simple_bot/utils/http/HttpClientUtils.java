package com.simple_bot.utils.http;

import com.simple_bot.app.AppContext;
import com.simple_bot.dto.BaseResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Иван
 * @version $Id$
 */
public class HttpClientUtils {
    private static final String USER_AGENT = "User-Agent";
    private static final String ACCEPT = "Accept";
    private static final String LANGUAGE = "Accept-Language";
    private static final String COOKIE = "Cookie";

    public static BaseResponse doPost(HttpRequestContext context) throws IOException {
        if (context.isLog()) {
            System.out.println(String.format("POST %s", context.getUrl()));
        }

        if (context.getUrl() == null) {
            throw new IllegalArgumentException("Url param is required");
        }

        HttpPost request = (HttpPost) getRequest(context, HttpPost.class);
        return execute(request, context);
    }

    public static BaseResponse doGet(HttpRequestContext context) throws IOException {
        if (context.isLog()) {
            System.out.println(String.format("GET %s", context.getUrl()));
        }

        if (context.getUrl() == null) {
            throw new IllegalArgumentException("Url param is required");
        }

        HttpGet request = (HttpGet) getRequest(context, HttpGet.class);
        return execute(request, context);
    }

    private static BaseResponse execute(HttpRequestBase request, HttpRequestContext context) throws IOException {
        HttpResponse response = HttpClientLazySingleton.getInstance().execute(request);

        BaseResponse res = new BaseResponse(IOUtils.toString(response.getEntity().getContent()),
                response.getStatusLine().getStatusCode(), getHeaders(response));

        if (context.isLog()) {
            System.out.println(String.format("Content: %s\n, Status:%s\n, Headers:%s", res.getContent(),
                    res.getCode(), res.getHeaders()));
        }

        return res;
    }

    private static <T extends HttpRequestBase> HttpRequestBase getRequest(HttpRequestContext context,
                                                                          Class<T> responseClass)
            throws UnsupportedEncodingException {
        HttpRequestBase request;
        if (responseClass.equals(HttpGet.class)) {
            request = new HttpGet(context.getUrl());
        } else {
            request = new HttpPost(context.getUrl());
            if (CollectionUtils.isNotEmpty(context.getParams())) {
                ((HttpPost) request).setEntity(new UrlEncodedFormEntity(context.getParams()));
            }
        }

        request.setHeader(USER_AGENT, AppContext.getProperty("header.agent"));
        request.setHeader(ACCEPT, AppContext.getProperty("header.accept"));
        request.setHeader(LANGUAGE, AppContext.getProperty("header.lang"));
        request.setHeader(COOKIE, context.getCookies());

        if (MapUtils.isNotEmpty(context.getHeaders())) {
            context.getHeaders().entrySet().forEach(entry -> request.setHeader(entry.getKey(), entry.getValue()));
        }

        return request;
    }

    private static Map<String, String> getHeaders(HttpResponse response) {
        if (ArrayUtils.isNotEmpty(response.getAllHeaders())) {
            return Arrays.stream(response.getAllHeaders()).collect(Collectors.toMap(
                    Header::getName,
                    Header::getValue
            ));
        }
        return Collections.emptyMap();
    }
}
