import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class Dom0103 {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse("0101.xml");
			Element root = doc.getDocumentElement();
			
			System.out.println("Document オブジェクトを取得： " + doc);
			System.out.printf("root element = <%s>\n", root.getNodeName());
			
			Node firstChild = root.getFirstChild();
			System.out.printf("<%s>'s first child = <%s>\n", root.getNodeName(), firstChild.getNodeName());
			
			Node nextSibling = firstChild.getNextSibling();
			System.out.printf("<%s>'s next sibling = <%s>\n", firstChild.getNodeName(), nextSibling.getNodeName());
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
