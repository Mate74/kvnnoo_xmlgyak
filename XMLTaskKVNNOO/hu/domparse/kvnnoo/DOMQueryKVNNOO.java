package hu.domparse.kvnnoo;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerException;

import javax.xml.xpath.XPathExpressionException;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryKVNNOO {

	public static void main(String[] args) throws SAXException,
	IOException, ParserConfigurationException, XPathExpressionException, TransformerException {
		
		File xmlFile = new File("XMLKVNNOO.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("Elektronikai_cikkek");
		printNodeList(nList);
		
		NodeList nList1 = doc.getElementsByTagName("Vasarlo");
		printNodeList(nList1);
		
		NodeList nList2 = doc.getElementsByTagName("Ugyfelszolgalat");
		printNodeList(nList2);
		}
	
	private static void printNodeList(NodeList nodeList)  {
	for (int i = 0; i < nodeList.getLength(); i++)   {
		Node elemNode = nodeList.item(i);  
		if (elemNode.getNodeType() == Node.ELEMENT_NODE )   
			{  
				System.out.println("Node Name =" + elemNode.getNodeName());  
				
				if (elemNode.hasAttributes())   
				{  
				NamedNodeMap nodeMap = elemNode.getAttributes();  
				for (int j = 0; j < nodeMap.getLength(); j++)   
				{  
				Node node = nodeMap.item(j);  
				System.out.println("Attribute Name:  " + node.getNodeName());  
				System.out.println("Attribute Value: " + node.getNodeValue());  
				}  
				}  
				
				if (elemNode.hasChildNodes())   
				{  
				//Rekurzív hívás ha még van gyerekelem
				printNodeList(elemNode.getChildNodes());
				}  
			} 
			else {
				System.out.println(elemNode.getTextContent());
			}
		} 
	}  
}
