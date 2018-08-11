package com.simple_bot;

import org.apache.commons.io.IOUtils;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public interface AbstractXSLTTest {
    default String xmlToHtml(String path) throws TransformerException {
        Source xml = new StreamSource(this.getClass().getResourceAsStream(path + ".xml"));
        Source xslt = new StreamSource(this.getClass().getResourceAsStream(path + ".xslt"));

        StringWriter sw = new StringWriter();
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer trasform = tFactory.newTransformer(xslt);
        trasform.transform(xml, new StreamResult(sw));
        return sw.toString();
    }

    default void checkXmlToHtml(String path) throws IOException, TransformerException {
        assertEquals(getResult(path), xmlToHtml(path));
    }

    default String getResult(String path) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(path + ".html"));
    }
}
