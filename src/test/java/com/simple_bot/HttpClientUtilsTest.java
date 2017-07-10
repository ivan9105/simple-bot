package com.simple_bot;

import com.simple_bot.app.AppContext;
import com.simple_bot.dto.BaseResponse;
import com.simple_bot.utils.HtmlReportUtils;
import com.simple_bot.utils.builder.FormMapUtils;
import com.simple_bot.utils.http.HttpClientUtils;
import com.simple_bot.utils.http.HttpRequestContext;
import org.apache.http.NameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Иван
 * @version $Id$
 */
public class HttpClientUtilsTest extends TestCase {
    private static final String GOOGLE_ACCOUNTS_URL = "https://accounts.google.com/ServiceLoginAuth";
    private static final String GOOGLE_MAIL_URL = "https://mail.google.com/mail/";

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
    public void doScenarioTest() throws IOException {
        BaseResponse get1 = HttpClientUtils.doGet(new HttpRequestContext.Builder()
                .setUrl(GOOGLE_ACCOUNTS_URL)
                .build());

        List<NameValuePair> formParams = HttpClientUtils.getFormParams(get1.getContent(),
                FormMapUtils.of("Email", "username", "Passwd-hidden", "password"), "gaia_loginform");

        //Todo
        BaseResponse post1 = HttpClientUtils.doPost(new HttpRequestContext.Builder()
                .setUrl(GOOGLE_ACCOUNTS_URL)
                .setHeaders(FormMapUtils.of(
                        "", "",
                        "", "",
                        "", "",
                        "", "",
                        "", ""
                ))
                .setParams(formParams)
                .build());



        HtmlReportUtils.buildReport("doScenarioTest");
        HtmlReportUtils.clear();
    }


}
