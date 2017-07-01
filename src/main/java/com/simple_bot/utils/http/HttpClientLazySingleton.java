package com.simple_bot.utils.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author ����
 * @version $Id$
 */
public class HttpClientLazySingleton {
    private static class HttpClientHolder {
        private static final HttpClient INSTANCE = HttpClientBuilder.create().build();
    }

    public static HttpClient getInstance() {
        return HttpClientHolder.INSTANCE;
    }
}
