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

import org.xml.sax.SAXException;

public class DOMReadKVNNOO {

	public static void main(String argv[]) throws SAXException,
	IOException, ParserConfigurationException, TransformerException {
	
		File xmlFile = new File("XMLKVNNOO.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
			
		writeXml(doc, System.out);
		
		}
	
	private static void writeXml(Document doc,OutputStream output)
			throws TransformerException, UnsupportedEncodingException {
	
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	
	Transformer transformer = transformerFactory.newTransformer();
	
	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
	
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(output);
	
	transformer.transform(source, result);

	}

}
