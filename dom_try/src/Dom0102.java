import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Dom0102 {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("0101.xml");

			Element root = doc.getDocumentElement();
			String name = root.getNodeName();
			
			System.out.println("Document オブジェクトを取得： " + doc);
			System.out.println("ルート要素のタグ名： " + name);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
