package com.simple_bot.utils.http;

import com.simple_bot.app.AppContext;
import com.simple_bot.dto.BaseResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
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

    public static void doPost(HttpRequestContext context) {
        if (context.getUrl() == null) {
            throw new IllegalArgumentException("Url param is required");
        }

        HttpPost post = new HttpPost(context.getUrl());

        post.setHeader(USER_AGENT, AppContext.getProperty("header.agent"));
    }

    public static BaseResponse doGet(HttpRequestContext context) throws IOException {
        if (context.getUrl() == null) {
            throw new IllegalArgumentException("Url param is required");
        }

        HttpGet request = new HttpGet(context.getUrl());

        request.setHeader(USER_AGENT, AppContext.getProperty("header.agent"));
        request.setHeader(ACCEPT, AppContext.getProperty("header.accept"));
        request.setHeader(LANGUAGE, AppContext.getProperty("header.lang"));

        HttpResponse response = HttpClientLazySingleton.getInstance().execute(request);
        return new BaseResponse(IOUtils.toString(response.getEntity().getContent()),
                response.getStatusLine().getStatusCode(), getHeaders(response));
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
