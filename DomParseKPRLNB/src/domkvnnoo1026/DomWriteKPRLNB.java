package domkvnnoo1026;

import java.io.File;

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
import org.w3c.dom.Element;

public class DomWriteKPRLNB {

	public static void main(String[] args) throws ParserConfigurationException,TransformerException {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("domkprlnb","users");
		doc.appendChild(root);
		root.appendChild(createUser(doc,"1","Pal","Kiss","programmer"));
		root.appendChild(createUser(doc,"2","Piroska","Zold","Writer"));
		root.appendChild(createUser(doc,"3","Alma","Gordon","teacher"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource source= new DOMSource(doc);
		File myFile= new File("usersKVNNOO.xml");
		
		StreamResult console= new StreamResult(System.out);
		StreamResult file= new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
		
		
	}

}
