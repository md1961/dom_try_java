import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Dom0105 {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse("0101.xml");
			Element root = doc.getDocumentElement();
			
			System.out.println("Document オブジェクトを取得： " + doc);
			System.out.printf("root element = <%s>\n", root.getNodeName());
			
			NodeList childNodes = root.getChildNodes();
			for (int i = 0, size = childNodes.getLength(), count = 1; i < size; i++) {
				Node child = childNodes.item(i);
				if (isTextNode(child)) {
					continue;
				}
				System.out.printf("<%s>'s child node No.%d = <%s>\n", root.getNodeName(), count++, child.getNodeName());
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private static final String NODE_NAME_OF_TEXT_NODE = "#text";
	
	private static boolean isTextNode(Node node) {
		return NODE_NAME_OF_TEXT_NODE.equals(node.getNodeName());
	}
}
