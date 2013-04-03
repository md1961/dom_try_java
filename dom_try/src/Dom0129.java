import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.XmlUtil;


public class Dom0129 {

	private static final String TARGET_XML_FILENAME = "0109.xml";
	
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		Document doc = null;
		try {
			doc = db.parse(TARGET_XML_FILENAME);
		} catch (SAXException | IOException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		System.out.println("==> Before <==");
		System.out.println(XmlUtil.toPrettyString(doc));
		
		NodeList nodeListPrice = doc.getElementsByTagName("price");
		Node firstNodePrice = nodeListPrice.item(0);
		Node parentNode = firstNodePrice.getParentNode();
		parentNode.appendChild(firstNodePrice);
		
		System.out.println();
		System.out.println("==> After <==");
		System.out.println(XmlUtil.toPrettyString(doc));
	}
}
