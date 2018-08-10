package com.simple_bot.xsl;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class XSLTTest {
    @Test
    public void test_xmlToHtmlForEach() throws TransformerException, IOException {
        checkXmlToHtml("/xsl/for_each/for_each");
    }

    private void checkXmlToHtml(String path) throws IOException, TransformerException {
        assertEquals(getResult(path), xmlToHtml(path));
    }

    private String getResult(String path) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(path + ".html"));
    }

    private String xmlToHtml(String path) throws TransformerException {
        Source xml = new StreamSource(this.getClass().getResourceAsStream(path + ".xml"));
        Source xslt = new StreamSource(this.getClass().getResourceAsStream(path + ".xslt"));

        StringWriter sw = new StringWriter();
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer trasform = tFactory.newTransformer(xslt);
        trasform.transform(xml, new StreamResult(sw));
        return sw.toString();
    }


}
