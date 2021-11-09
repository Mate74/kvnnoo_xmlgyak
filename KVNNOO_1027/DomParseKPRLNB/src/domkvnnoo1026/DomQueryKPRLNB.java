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

public class DomQueryKPRLNB {

	public static void main(String argv[]) throws SAXException,
	IOException, ParserConfigurationException {
	
		File xmlFile = new File("carsKVNNOO.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.printf("Root element :\n\n",doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("supercars");
		for(int i = 0;i<nList.getLength();i++){
			
			org.w3c.dom.Node nNode =  nList.item(i);
			System.out.println("Current Element:"+nNode.getNodeName());
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String company = nNode.getAttributes().getNamedItem("company").getTextContent();
                System.out.println("supercarscompany:"+company);
                if ("Ferrari".equals(company.trim())) {
                	
                    NodeList childNodes = nNode.getChildNodes();
                     Element elem = (Element) nNode;
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        org.w3c.dom.Node item = childNodes.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {
                                if ("carname".equalsIgnoreCase(item.getNodeName())) {
                                	String type = item.getAttributes().getNamedItem("type").getTextContent();
                                	if ("formula one".equals(type.trim())) {
	                    				org.w3c.dom.Node node1 =elem.getElementsByTagName("carname").item(0);
	                    				String cname1 = node1.getTextContent();
	                    				System.out.println("car name   :"+cname1);
	                    				System.out.println("car type    :"+type);
                                	}
                                	if ("sports".equals(type.trim())) {
	                    				org.w3c.dom.Node node2 =elem.getElementsByTagName("carname").item(1);
	                    				String cname2= node2.getTextContent();
	                    				System.out.println("car name   :"+cname2);
	                    				System.out.println("car type    :"+type);
                                	}
                                }
                        }
                    }
                }
                if ("Lamborghini".equals(company.trim())) {
                						Element elem = (Element) nNode;
                
	                    				org.w3c.dom.Node node1 =elem.getElementsByTagName("carname").item(0);
	                    				String cname1 = node1.getTextContent();
	                    				System.out.println("car name   :"+cname1);
	                    				System.out.println("car type    :");

	                    				
	                    				org.w3c.dom.Node node2 =elem.getElementsByTagName("carname").item(1);
	                    				String cname2= node2.getTextContent();
	                    				System.out.println("car name   :"+cname2);
	                    				System.out.println("car type    :");
	                    				
	                    				org.w3c.dom.Node node3 =elem.getElementsByTagName("carname").item(2);
	                    				String cname3= node3.getTextContent();
	                    				System.out.println("car name   :"+cname3);
	                    				System.out.println("car type    :");
                }


            }
	        System.out.printf("\n");
		}
	}
}
