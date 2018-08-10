package com.simple_bot.xsl;

import com.simple_bot.AbstactXSLTTest;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class XSLTTest extends AbstactXSLTTest {
    @Test
    public void test_xmlToHtmlForEach() throws TransformerException, IOException {
        checkXmlToHtml("/xsl/for_each/for_each");
    }

    @Test
    public void test_xmlToHtmlValueOf() throws TransformerException, IOException {
        //Todo
    }

    private void checkXmlToHtml(String path) throws IOException, TransformerException {
        assertEquals(getResult(path), xmlToHtml(path));
    }

    private String getResult(String path) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(path + ".html"));
    }




}
