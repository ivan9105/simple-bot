package com.simple_bot;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringWriter;

public class AbstactXSLTTest {
    protected String xmlToHtml(String path) throws TransformerException {
        Source xml = new StreamSource(this.getClass().getResourceAsStream(path + ".xml"));
        Source xslt = new StreamSource(this.getClass().getResourceAsStream(path + ".xslt"));

        StringWriter sw = new StringWriter();
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer trasform = tFactory.newTransformer(xslt);
        trasform.transform(xml, new StreamResult(sw));
        return sw.toString();
    }
}
