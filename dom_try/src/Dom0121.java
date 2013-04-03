import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import util.XmlUtil;


public class Dom0121 {
	
	private static final String TARGET_XML_FILENAME = "0106.xml";

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
		
		Node commentNode = doc.createComment("会社の行事");
		root.insertBefore(commentNode, root.getFirstChild());
		
		Node nodeEvent = doc.createElement("event");
		Node cdataNode = doc.createCDATASection("スキー&スノーボード");
		nodeEvent.appendChild(cdataNode);
		root.appendChild(nodeEvent);
		
		System.out.println(XmlUtil.toPrettyString(doc));
	}
}
