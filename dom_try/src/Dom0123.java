import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.XmlUtil;


public class Dom0123 {
	
	private static final String TARGET_XML_FILENAME = "0101.xml";

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace(System.err);
		}
		
		Document doc = null;
		try {
			doc = db.parse(TARGET_XML_FILENAME);
		} catch (SAXException | IOException e) {
			e.printStackTrace(System.err);
		}
		
		Node newProductNode = doc.createElement("製品");
		
		NodeList priceNodes = doc.getElementsByTagName("price");
		Node firstPriceNode = priceNodes.item(0);
		
		Node rootNode = doc.getDocumentElement();
		rootNode.replaceChild(newProductNode, firstPriceNode);
		
		System.out.println(XmlUtil.toPrettyString(doc));
	}
}
