import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class Dom0101 {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse("0101.xml");
			
			System.out.println("Document オブジェクトを取得： " + doc);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
