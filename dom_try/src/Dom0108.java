import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Dom0108 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: java %s xmlFilename\n", Dom0108.class.getName());
			System.exit(0);
		}
		
		String xmlFilename = args[0];
		File xmlFile = new File(xmlFilename);
		if (! xmlFile.exists()) {
			System.err.printf("Cannot find file '%s'\n", xmlFilename);
			System.exit(0);
		}
		
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
			doc = db.parse(xmlFilename);
		} catch (IOException | SAXException e) {
			System.err.println(e);
			System.exit(1);
		}
		
		traceTree(doc.getDocumentElement());
	}
	
	private static void traceTree(Node node) {
		traceTree(node, 0);
	}
	
	private static final String UNIT_INDENT = StringUtils.repeat(" ", 4);
	
	private static void traceTree(Node node, int indentLevel) {
		String indent = StringUtils.repeat(UNIT_INDENT, indentLevel);
		
		int nodeType = node.getNodeType();
		if (nodeType == Node.ELEMENT_NODE) {
			System.out.printf("%s<%s>\n", indent, node.getNodeName());
			
			NodeList nodesChildren = node.getChildNodes();
			for (int i = 0, size = nodesChildren.getLength(); i < size; i++) {
				traceTree(nodesChildren.item(i), indentLevel + 1);
			}
		} else if (nodeType == Node.TEXT_NODE) {
			String nodeValue = node.getNodeValue();
			String displayingValue = StringUtils.replace(nodeValue      , "\n", "\\n");
			displayingValue        = StringUtils.replace(displayingValue, "\t", "\\t");
			System.out.printf("%sTextNode[%s]\n", indent, displayingValue);
		}
	}
}
