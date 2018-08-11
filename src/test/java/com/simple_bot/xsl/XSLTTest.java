package com.simple_bot.xsl;

import com.simple_bot.AbstractXSLTTest;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XSLTTest implements AbstractXSLTTest {
    @Test
    public void test_xmlToHtmlForEach() throws TransformerException, IOException {
        checkXmlToHtml("/xsl/for_each/for_each");
    }

    @Test
    public void test_xmlToHtmlValueOf() throws TransformerException, IOException {
        checkXmlToHtml("/xsl/value_of/value_of");
    }
}
