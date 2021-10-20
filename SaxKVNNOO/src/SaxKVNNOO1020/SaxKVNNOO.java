package SaxKVNNOO1020;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxKVNNOO {
	public static void main(String[] args){
		try{
			/*Dokumentumolvasó létrehozása a  */
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser(); 
			SaxHandler handler = new SaxHandler();
			saxParser.parse(new File("szemelyek.xml"), handler);
		}catch( ParserConfigurationExecption | SAXException | IOException e){
			e.printStackTrace();
		}
		
	}
}
class SaxHandler extends DefaultHandler {
	
	private int indent = 0;
	
	private String formatAttributes(Attributes attributes)throws SAXException{
		int attrLength = attributes.getLength();
		if(attrLength == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder(",{");
		for (int i =0; i < attrLength; i++){
			sb.append(attributes.getLocalName(i)+ ":" + attributes.getValue(i));
			if(i<attrLength-1){
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
		}
	private void indent()throws SAXException {
		for(int i=0; i<indent;i++){
			System.out.print("  ");
		}
	}
	public void startElement(String uri,String localname,String qName, Attributes attributes)throws SAXException{
		indent++;
		indent();
		System.out.println(qName+formatAttributes(attributes)+"start");
	}
	public void endElement(String uri,String localname,String qName)throws SAXException{
		indent++;
		indent();
		System.out.println(qName+"end");
	}
	public void character(char ch[], int start, int length)throws SAXException{
		String chars= new String(ch, start, length).trim();
		if(!chars.isEmpty()){
			indent++;
			indent();
			indent--;
			System.out.println(chars);
		}
	}
}

	
	

