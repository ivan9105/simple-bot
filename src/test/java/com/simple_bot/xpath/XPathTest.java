package com.simple_bot.xpath;

import com.simple_bot.AbstractXPathTest;
import com.simple_bot.AbstractXSLTTest;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class XPathTest implements AbstractXSLTTest, AbstractXPathTest {
    @Test
    public void test_Predicates() throws IOException, TransformerException {
        checkXmlToHtml("/xsl/xpath/predicates/predicates");
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
    public void test_SelectingSeveralPaths() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        Document doc = getDocument("/xsl/xpath/select_nodes/select_nodes.xml");
        XPath xpath = getXPath();

        NodeList nodeList = evaluateNodeList(doc, xpath, "//woman | //man");
        assertEquals(7, nodeList.getLength());
    }

    @Test
    public void test_Axes() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        Document doc = getDocument("/xsl/xpath/axes/axes.xml");
        XPath xpath = getXPath();

        NodeList nodeList = evaluateNodeList(doc, xpath, "/class/student/preceding-sibling::comment()");
        assertEquals(nodeList.item(0).getNodeValue(), " Comment: This is a list of student ");

        nodeList = evaluateNodeList(doc, xpath, "//student/firstname/ancestor::student/firstname");
        assertEquals(nodeList.item(1).getChildNodes().item(0).getNodeValue(), "Vaneet");

        nodeList = evaluateNodeList(doc, xpath, "//student/firstname/ancestor::*");
        assertEquals(nodeList.getLength(), 4);

        nodeList = evaluateNodeList(doc, xpath, "/class/student/child::firstname");
        assertEquals(nodeList.item(0).getChildNodes().item(0).getNodeValue(), "Dinkar");

        nodeList = evaluateNodeList(doc, xpath, "/class/child::*");
        assertEquals(nodeList.getLength(), 3);
    }
}
