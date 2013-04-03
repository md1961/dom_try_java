import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Dom0106 {

	private static final String TARGET_XML_FILE = "0102.xml";
	
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(TARGET_XML_FILE);
			
			NodeList nodesBook = doc.getElementsByTagName("book");
			int numNodesBook = nodesBook.getLength();
			System.out.printf("There are %d <book>'s\n", numNodesBook);
			
			for (int i = 0, size = numNodesBook; i < size; i++) {
				Node nodeBook = nodesBook.item(i);
				Element elementBook = (Element)nodeBook;
				NodeList nodesMemo = elementBook.getElementsByTagName("memo");
				System.out.printf("<book> #%d has %d <memo>'s\n", i + 1, nodesMemo.getLength());
			}
		} catch (ParserConfigurationException e1) {
			System.err.println(e1);
		} catch (IOException e2) {
			System.err.println(e2);
		} catch (SAXException e3) {
			System.err.println(e3);
		}
	}
}
