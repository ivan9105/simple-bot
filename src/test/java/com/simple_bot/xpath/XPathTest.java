package com.simple_bot.xpath;

import com.simple_bot.AbstractXPathTest;
import com.simple_bot.AbstractXSLTTest;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class XPathTest implements AbstractXSLTTest, AbstractXPathTest {
    @Test
    public void test_Predicates() {
        //Todo for each predicates
    }

    @Test
    public void test_SelectingNodes() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        Document doc = getDocument("/xsl/xpath/select_nodes/select_nodes.xml");
        XPath xpath = getXPath();

        NodeList nodeList = evaluateNodeList(doc, xpath, "//*[@name='Ivan']");
        checkNodeListAttributes(nodeList, "name", "Ivan", "Ivan");
        checkNodeListAttributes(nodeList, "surname", "Fedorov", "Zinin");

        nodeList = evaluateNodeList(doc, xpath, "//*[contains(@name,'a')]");
        checkNodeListAttributes(nodeList, "name", "Ivan", "Ivan", "Oksana", "Maria");

        nodeList = evaluateNodeList(doc, xpath, "//*[@surname]");
        checkNodeListAttributes(nodeList, "surname", "Fedorov", "Zinin", "Chernov", "Ivanova", "Testova");

        nodeList = evaluateNodeList(doc, xpath, "//*[@name and @surname]");
        checkNodeListAttributes(nodeList, "surname", "Fedorov", "Zinin", "Chernov", "Ivanova");
    }

    @Test
    public void test_SelectingUnknownNodes() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        Document doc = getDocument("/xsl/xpath/select_nodes/select_nodes.xml");
        XPath xpath = getXPath();

        NodeList nodeList = evaluateNodeList(doc, xpath, "//woman");
        assertEquals(3, nodeList.getLength());

        nodeList = evaluateNodeList(doc, xpath, "/entries/woman");
        assertEquals(3, nodeList.getLength());

        nodeList = evaluateNodeList(doc, xpath, "/entries/*");
        assertEquals(7, nodeList.getLength());
    }

    @Test
    public void test_SelectingSeveralPaths() {
        //Todo
    }

    //Todo for each captions
}
