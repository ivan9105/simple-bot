package com.simple_bot.xquery;

import com.simple_bot.AbstractXQueryTest;
import org.junit.Test;

import javax.xml.xquery.XQException;
import java.io.IOException;

public class XQueryTest implements AbstractXQueryTest {
    @Test
    public void test_Select() throws XQException, IOException {
        checkEvaluate("/xsl/xquery/select/select");
    }

    @Test
    public void test_Order() throws XQException, IOException {
        checkEvaluate("/xsl/xquery/order/order");
    }

    @Test
    public void test_Html() throws XQException, IOException {
        checkEvaluate("/xsl/xquery/html/html");
    }

    @Test
    public void test_If() throws XQException, IOException {
        checkEvaluate("/xsl/xquery/if/if");
    }
}
