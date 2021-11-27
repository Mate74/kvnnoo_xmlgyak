package hu.domparse.kvnnoo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DOMModifyKVNNOO {

	public static void main(String[] args) throws SAXException,
	IOException, ParserConfigurationException, TransformerException {
		
		File xmlFile = new File("XMLKVNNOO.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		NodeList nlista = doc.getElementsByTagName("Elektronikai_cikkek");
		Modify(nlista,"Televizio","Samsung","Sony");
		
		NodeList nlista1 = doc.getElementsByTagName("Vasarlo");
		Modify(nlista1,"Bankszamlaszam","1171234567891011","1175555555555555");
		
		NodeList nlista2 = doc.getElementsByTagName("Ugyfelszolgalat");
		Modify(nlista2,"UE-mail","electrolux@gmail.com","LG.hotmail.com");
		
		
		writeXml(doc, System.out);
	}
	private static void writeXml(Document doc,
            OutputStream output)
throws TransformerException, UnsupportedEncodingException {

TransformerFactory transformerFactory = TransformerFactory.newInstance();

Transformer transformer = transformerFactory.newTransformer();


transformer.setOutputProperty(OutputKeys.INDENT, "yes");
transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

DOMSource source = new DOMSource(doc);
StreamResult result = new StreamResult(output);

transformer.transform(source, result);

}
	private static void Modify(NodeList nList,String tag,String originalcontent,String newcontent)
	{
		for(int i = 0;i<nList.getLength();i++){
			
			Node nNode =  nList.item(i);
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodes = nNode.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node item =childNodes.item(j);
                        if (item.getNodeType() == Node.ELEMENT_NODE) {
                                if (tag.equalsIgnoreCase(item.getNodeName())) {
                                	if(item.getTextContent().equalsIgnoreCase(originalcontent)) {
                                		item.setTextContent(newcontent);
                                	}
                                }
                        }
                    }
            }
			
		}
	}
}
