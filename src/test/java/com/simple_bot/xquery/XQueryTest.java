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
}
