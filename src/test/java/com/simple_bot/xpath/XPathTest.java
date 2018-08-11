package com.simple_bot.xpath;

import com.simple_bot.AbstractXPathTest;
import com.simple_bot.AbstractXSLTTest;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileInputStream;
import java.io.IOException;

import static javax.xml.parsers.DocumentBuilderFactory.newInstance;
import static javax.xml.xpath.XPathConstants.NODESET;
import static org.junit.Assert.assertEquals;

public class XPathTest implements AbstractXSLTTest, AbstractXPathTest {
    @Test
    public void test_Predicates() {
        //Todo for each predicates
    }

    @Test
    public void test_SelectingNodes() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        //TOdo common class
        DocumentBuilderFactory dbf = newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(this.getClass().getResourceAsStream("/xsl/xpath/select_nodes/select_nodes.xml"));
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        NodeList nodeList = (NodeList) xpath.evaluate("//*[@name='Ivan']", doc, NODESET);
        assertEquals(2, nodeList.getLength());
        assertEquals(nodeList.item(0).getAttributes().getNamedItem("name").getNodeValue(), "Ivan");
        assertEquals(nodeList.item(1).getAttributes().getNamedItem("name").getNodeValue(), "Ivan");
        assertEquals(nodeList.item(0).getAttributes().getNamedItem("surname").getNodeValue(), "Fedorov");
        assertEquals(nodeList.item(1).getAttributes().getNamedItem("surname").getNodeValue(), "Zinin");
        //Todo
    }

    @Test
    public void test_SelectingUnknownNodes() {
        //Todo
    }

    @Test
    public void test_SelectingSeveralPaths() {
        //Todo
    }

    //Todo for each captions
}
