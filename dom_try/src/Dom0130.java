import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.XmlUtil;


public class Dom0130 {

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
		
		NodeList nodeListMemo = doc.getElementsByTagName("memo");
		Node firstNodeMemo = nodeListMemo.item(0);
		Node parentNode = firstNodeMemo.getParentNode();
		parentNode.insertBefore(firstNodeMemo, parentNode.getFirstChild());
		
		System.out.println();
		System.out.println("==> After <==");
		System.out.println(XmlUtil.toPrettyString(doc));
	}
}
