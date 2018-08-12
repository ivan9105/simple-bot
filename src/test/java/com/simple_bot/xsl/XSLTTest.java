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

    @Test
    public void test_xmlToHtmlSort() throws TransformerException, IOException {
        checkXmlToHtml("/xsl/sort/sort");
    }

    @Test
    public void test_xmlToHtmlIf()throws TransformerException, IOException {
        checkXmlToHtml("/xsl/if/if");
    }

    @Test
    public void test_xmlToHtmlChoose()throws TransformerException, IOException {
        checkXmlToHtml("/xsl/choose/choose");
    }

    @Test
    public void test_xmlToHtmlApplyTemplates()throws TransformerException, IOException {
        checkXmlToHtml("/xsl/apply_templates/apply_templates");
    }
}
