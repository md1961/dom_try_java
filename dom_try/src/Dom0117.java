import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import util.XmlUtil;


public class Dom0117 {
	
	private static final String TARGET_XML_FILENAME = "0101.xml";

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println(e);
			System.exit(1);
		}
		
		Document doc = null;
		try {
			doc = db.parse(TARGET_XML_FILENAME);
		} catch (IOException | SAXException e) {
			System.err.println(e);
			System.exit(1);
		}
		
		Element root = doc.getDocumentElement();
		Node nodePrice = root.getElementsByTagName("price").item(0);
		
		Node nodeCode = doc.createElement("code");
		root.insertBefore(nodeCode, nodePrice);
		
		System.out.println(XmlUtil.toPrettyString(doc));
	}
}
