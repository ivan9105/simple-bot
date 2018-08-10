package com.simple_bot.client;

import com.simple_bot.AbstractClientTestCase;
import com.simple_bot.app.AppContext;
import com.simple_bot.dto.BaseResponse;
import com.simple_bot.utils.builder.MapUtils;
import com.simple_bot.utils.http.HttpClientUtils;
import com.simple_bot.utils.http.HttpRequestContext;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static com.simple_bot.utils.http.HttpClientUtils.*;
import static org.junit.Assert.assertTrue;


/**
 * @author ivan-melnikov
 */
public class HttpClientUtilsTest extends AbstractClientTestCase {
    private static final String GOOGLE_ACCOUNTS_URL = "https://accounts.google.com/ServiceLoginAuth";
    private static final String GOOGLE_MAIL_URL = "https://mail.google.com/mail/";
    private static final String GOOGLE_ACCOUNTS_HOST = "accounts.google.com";
    private static final String GOOGLE_CONNECTION = "keep-alive";
    private static final String GOOGLE_ACCOUNTS_REFERRER = "https://accounts.google.com/ServiceLoginAuth";
    private static final String GOOGLE_ACCOUNTS_CONTENT_TYPE = "application/x-www-form-urlencoded";

    @Before
    @Override
    public void setUp() {
        super.setUp();

        AppContext.setProperty("http.logging", "true");
        AppContext.setProperty("http.report", "true");
    }

    @After
    public void tearDown() {
        AppContext.setProperty("http.logging", "false");
        AppContext.setProperty("http.report", "false");
    }

    @Test
    public void googleAuthScenarioTest() throws IOException {
        BaseResponse get1 = HttpClientUtils.doGet(new HttpRequestContext.Builder()
                .setUrl(GOOGLE_ACCOUNTS_URL)
                .build());

        Map<String, String> params = MapUtils.of(
                "Email", "ivan.melnikov.haulmont3",
                "Passwd", "050391zx");

        Set<NameValuePair> formParams = HttpClientUtils.getFormParams(get1.getContent(), params, "gaia_loginform");
        formParams.add(new BasicNameValuePair("Passwd", "050391zx"));

        BaseResponse post1 = HttpClientUtils.doPost(new HttpRequestContext.Builder()
                .setUrl(GOOGLE_ACCOUNTS_URL)
                .setHeaders(MapUtils.of(
                        HOST, GOOGLE_ACCOUNTS_HOST,
                        CONNECTION, GOOGLE_CONNECTION,
                        REFERRER, GOOGLE_ACCOUNTS_REFERRER,
                        CONTENT_TYPE, GOOGLE_ACCOUNTS_CONTENT_TYPE
                ))
                .setParams(formParams)
                .build());

        if (post1.getCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
            String content1 = post1.getContent();
            assertTrue(content1.contains("Moved Temporarily"));
            String confirmAccountUrl = content1.substring(
                    content1.indexOf("https://accounts.google.com"),
                    content1.indexOf("\">here"));
            //Todo dubug using fiddler or other http analyzer
        } else {
            BaseResponse get2 = HttpClientUtils.doGet(new HttpRequestContext.Builder()
                    .setUrl(GOOGLE_MAIL_URL)
                    .build());
        }
        //Todo use JSoup


//        HtmlReportUtils.buildReport("doScenarioTest");
//        HtmlReportUtils.clear();
    }


}
