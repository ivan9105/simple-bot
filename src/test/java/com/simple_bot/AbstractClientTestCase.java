package com.simple_bot;

import com.simple_bot.app.AppContext;
import org.junit.Before;

/**
 * @author ivan-melnikov
 */
public class AbstractClientTestCase {
    @Before
    public void setUp() {
        AppContext.startContext();
    }
}
