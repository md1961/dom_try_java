import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import util.XmlUtil;


public class Dom0115 {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println(e);
			System.exit(1);
		}
		
		DOMImplementation domImpl = db.getDOMImplementation();
		
		Document doc = domImpl.createDocument(null, "book", null);
		
		System.out.println(XmlUtil.toPrettyString(doc));
	}
}
