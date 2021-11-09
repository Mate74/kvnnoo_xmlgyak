package domkvnnoo1026;

import java.io.File;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



import org.w3c.dom.Document;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyKPRLNB {

	public static void main(String[] args) throws SAXException,
	IOException, ParserConfigurationException, TransformerException {
		
		File xmlFile = new File("carsKVNNOO.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		
		NodeList nlista = doc.getElementsByTagName("cars");
		for(int i = 0;i<nlista.getLength();i++){
				
				org.w3c.dom.Node cNode =  nlista.item(i);
				if (cNode.getNodeType() == Node.ELEMENT_NODE) {
				
				        NodeList childNodes = cNode.getChildNodes();
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            org.w3c.dom.Node item = childNodes.item(j);
                            if (item.getNodeType() == Node.ELEMENT_NODE) {
                                if ("luxurycars".equalsIgnoreCase(item.getNodeName())) {
                                    cNode.removeChild(item);
                                }
                            }

                        }
				
				}
        }
		
		NodeList nList = doc.getElementsByTagName("supercars");
		for(int i = 0;i<nList.getLength();i++){
			
			org.w3c.dom.Node nNode =  nList.item(i);
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String company = nNode.getAttributes().getNamedItem("company").getTextContent();
                if ("Ferrari".equals(company.trim())) {

                    NodeList childNodes = nNode.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        org.w3c.dom.Node item = childNodes.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {
                                if ("carname".equalsIgnoreCase(item.getNodeName())) {
                                    String type = item.getAttributes().getNamedItem("type").getTextContent();
                                    if ("formula one".equals(type.trim())) {
                                    	item.setTextContent("Lamborghini 001");
                                    }
                                    if ("sports".equals(type.trim())) {
                                    	item.setTextContent("Lamborghini 002");
                                    }
                                }
                        }
                    }
                }

                if ("Ferrari".equals(company.trim())) {

                	nNode.getAttributes().getNamedItem("company").setTextContent("Lamborghini");
                }

            }
			
		}

       writeXml(doc, System.out);


	}
		private static void writeXml(Document doc,
	            OutputStream output)
	throws TransformerException, UnsupportedEncodingException {
	
	TransformerFactory transformerFactory = TransformerFactory.newInstance();

	Transformer transformer = transformerFactory.newTransformer();
	
	// pretty print
	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
	
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(output);
	
	transformer.transform(source, result);

}
}
