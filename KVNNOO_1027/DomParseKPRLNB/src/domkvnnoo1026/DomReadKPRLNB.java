package domkvnnoo1026;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadKPRLNB {
	public static void main(String argv[]) throws SAXException,
	IOException, ParserConfigurationException {
	
		File xmlFile = new File("usersKVNNOO.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("user");
		
		for(int i = 0;i<nList.getLength();i++){
			org.w3c.dom.Node nNode =  nList.item(i);
			System.out.println(" Current Element:"+nNode.getNodeName());
			if (nNode.getNodeType()==Node.ELEMENT_NODE){
				Element elem = (Element) nNode;
				
				String uid = elem.getAttribute("id");
				org.w3c.dom.Node node1 =elem.getElementsByTagName("firstname").item(0);
				String fname = node1.getTextContent();
				org.w3c.dom.Node node2 =elem.getElementsByTagName("lastname").item(0);
				String lname = node2.getTextContent();
				org.w3c.dom.Node node3 =elem.getElementsByTagName("profession").item(0);
				String pname = node3.getTextContent();
				
				System.out.println("User id:"+ uid);
				System.out.println("First name:"+fname);
				System.out.println("Last name:"+lname);
				System.out.println("Prof name:"+pname);
			}
			
		}
		}
		
	}
