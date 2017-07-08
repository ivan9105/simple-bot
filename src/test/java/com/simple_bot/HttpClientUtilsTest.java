package com.simple_bot;

import com.simple_bot.app.AppContext;
import com.simple_bot.dto.BaseResponse;
import com.simple_bot.utils.http.HttpClientUtils;
import com.simple_bot.utils.http.HttpRequestContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
    }

    @After
    public void tearDown() {
        AppContext.setProperty("http.logging", "false");
    }

    @Test
    public void doScenarioTest() throws IOException {
        BaseResponse response = HttpClientUtils.doGet(new HttpRequestContext.Builder()
                .setUrl(GOOGLE_ACCOUNTS_URL)
                .build());
    }

}
