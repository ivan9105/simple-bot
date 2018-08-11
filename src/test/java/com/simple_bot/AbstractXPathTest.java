package com.simple_bot;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

import static javax.xml.parsers.DocumentBuilderFactory.newInstance;
import static javax.xml.xpath.XPathConstants.NODESET;
import static org.junit.Assert.assertEquals;

public interface AbstractXPathTest {
    default Document getDocument(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(this.getClass().getResourceAsStream(path));
    }

    default XPath getXPath() {
        return XPathFactory.newInstance().newXPath();
    }

    default NodeList evaluateNodeList(Document doc, XPath xpath, String expression) throws XPathExpressionException {
        return (NodeList) xpath.evaluate(expression, doc, NODESET);
    }

    default void checkNodeListAttributes(NodeList nodeList, String attributeName, String... expectedValues) {
        int currentIndex = 0;
        for (String expectedValue : expectedValues) {
            assertEquals(nodeList.item(currentIndex).getAttributes().getNamedItem(attributeName).getNodeValue(), expectedValue);
            currentIndex++;
        }
    }
}
