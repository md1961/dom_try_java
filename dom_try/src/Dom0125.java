import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.XmlUtil;


public class Dom0125 {
	
	private static final String TARGET_XML_FILENAME = "0107.xml";

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
		
		NodeList nodeListMonth = doc.getElementsByTagName("month");
		Node firstNodeMonth = nodeListMonth.item(0);
		if (firstNodeMonth == null) {
			System.out.println("No <month> found");
			System.out.println();
			System.out.println(XmlUtil.toPrettyString(doc));
		} else {
			printXmlAndNumChilds(doc, "==> Original <==");
			
			Node parentNode = firstNodeMonth.getParentNode();
			parentNode.removeChild(firstNodeMonth);
			printXmlAndNumChilds(doc, "==> After remove child node <==");
			System.out.println();
			
			doc.normalize();
			printXmlAndNumChilds(doc, "==> After normalize <==");
		}
	}

	private static void printXmlAndNumChilds(Document doc, String caption) {
		System.out.println(caption);
		System.out.println(XmlUtil.toPrettyString(doc));
		Node rootNode = doc.getDocumentElement();
		System.out.println("Number of child nodes of <diary> = " + rootNode.getChildNodes().getLength());
	}
}