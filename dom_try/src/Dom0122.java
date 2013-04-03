import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.XmlUtil;


public class Dom0122 {
	
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
		
		Node rootNode = doc.getDocumentElement();
		NodeList childNodes = rootNode.getChildNodes();
		for (int i = 0, size = childNodes.getLength(); i < size; i++) {
			Node childNode = childNodes.item(i);
			System.out.println(childNode.getNodeName());
			if ("name".equals(childNode.getNodeName())) {
				childNode.getFirstChild().setNodeValue("テレビ");
				break;
			}
		}
		
		System.out.println(XmlUtil.toPrettyString(doc));
	}
}
