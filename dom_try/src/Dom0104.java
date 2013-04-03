import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class Dom0104 {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse("0101.xml");
			Element root = doc.getDocumentElement();
			
			System.out.println("Document オブジェクトを取得： " + doc);
			System.out.printf("root element = <%s>\n", root.getNodeName());
			
			Node lastChild = root.getLastChild();
			System.out.printf("<%s>'s last child = <%s>\n", root.getNodeName(), lastChild.getNodeName());
			
			Node prevSibling = lastChild.getPreviousSibling();
			System.out.printf("<%s>'s previous sibling = <%s>\n", lastChild.getNodeName(), prevSibling.getNodeName());
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
