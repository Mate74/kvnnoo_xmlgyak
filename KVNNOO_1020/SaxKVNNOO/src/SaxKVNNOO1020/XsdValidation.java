package SaxKVNNOO1020;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;
import java.io.File; // if you use File
import java.io.FileInputStream;
import java.io.IOException;
public class XsdValidation {
	public static void main(String[] args) {
		
	         boolean isValid = validateXMLSchema("src/SaxKVNNOO1020/macskakKVNNOO.xsd","src/SaxKVNNOO1020/macskakKVNNOO.xml");
	         
	         if(isValid){
	            System.out.println(  " Successful validation! " );
	         } else {
	            System.out.println( " Validation is not successful! " );
	            }
	      }
	      public static boolean validateXMLSchema(String xsdPath, String xmlPath){
	          try {
	        	  XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	        	  FileInputStream in = new FileInputStream(xmlPath);
	        	  XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	        	  SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        	  Schema schema = factory.newSchema(new File(xsdPath));
	        	  Validator validator = schema.newValidator();
	        	  validator.validate(new StAXSource(eventReader));
	          } catch (IOException e){
	             System.out.println("Exception: "+e.getMessage());
	             return false;
	          }catch(SAXException e1){
	             System.out.println("SAX Exception: "+e1.getMessage());
	             return false;
	          } catch (XMLStreamException e) {
				 System.out.println("XML Exception: "+e.getMessage());
				 return false;
			}
	    		
	          return true;
	    	
	       }
	}
