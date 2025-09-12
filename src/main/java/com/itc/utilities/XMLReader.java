package com.itc.utilities;
 
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.w3c.dom.*;
 
public class XMLReader {
 
    private Document document;
 
    /**
     * Default constructor: Automatically picks XML file using the caller class name.
     */
    public XMLReader() {
        try {
            String callingClassName = getCallingTestClassName();
            String xmlPath = "src/test/resources/xmlData/" + callingClassName + ".xml";
            File xmlFile = new File(xmlPath);

            if (!xmlFile.exists()) {
                System.out.println("XML file not found. Creating new file at: " + xmlPath);
                xmlFile.getParentFile().mkdirs(); // Ensure directory exists
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read or create XML file for test class: " + e.getMessage());
        }
    }

 
    /**
     * Manually load XML from a given file path (if needed).
     */
    public XMLReader(String filePath) throws Exception {
        try {
            File xmlFile = new File(filePath);
            if (!xmlFile.exists()) {
                throw new RuntimeException("XML file not found at path: " + filePath);
            }
 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read XML File: " + filePath);
        }
    }
 
    /**
     * Get data using tag name.
     */
    public String getData(String tagName) {
        try {
            NodeList nodeList = document.getElementsByTagName(tagName.trim());
            if (nodeList.getLength() > 0) {
                return nodeList.item(0).getTextContent();
            } else {
                throw new IllegalArgumentException("Tag <" + tagName + "> not found in XML");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    /**
     * Get data using tag name + attribute.
     */
    public String getData(String tagName, String attributeName, String attributeValue) {
        try {
            if (document == null)
                throw new IllegalStateException("XML Document is not loaded");
 
            NodeList nodeList = document.getElementsByTagName(tagName.trim());
 
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute(attributeName).equals(attributeValue)) {
                        return element.getTextContent();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * Utility: Find the calling test class name from stack trace.
     */
    private String getCallingTestClassName() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stack) {
            if (element.getClassName().startsWith("com.itc.testcases")) {
                String[] parts = element.getClassName().split("\\.");
                return parts[parts.length - 1];  // ClassName only
            }
        }
        throw new RuntimeException("Unable to determine calling test class name.");
    }
}