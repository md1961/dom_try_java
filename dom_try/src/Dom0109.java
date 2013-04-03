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


public class Dom0109 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: java %s xmlFilename\n", Dom0109.class.getName());
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

		String nodeValue = node.getNodeValue();
		switch(node.getNodeType()) {
			case Node.ELEMENT_NODE:
				System.out.printf("%s<%s>\n", indent, node.getNodeName());
				
				NodeList nodesChildren = node.getChildNodes();
				for (int i = 0, size = nodesChildren.getLength(); i < size; i++) {
					traceTree(nodesChildren.item(i), indentLevel + 1);
				}
				break;
			case Node.ATTRIBUTE_NODE:  // This block has no effect because attribute nodes are not included in child nodes
				System.out.printf("%s%s=\"%s\"\n", indent, node.getNodeName(), node.getNodeValue());
				break;
			case Node.TEXT_NODE:
				System.out.printf("%sTextNode[%s]\n", indent, substituteControlCodesWithReadables(nodeValue));
				break;
			case Node.COMMENT_NODE:
				System.out.printf("%sComment[%s]\n", indent, substituteControlCodesWithReadables(nodeValue));
				break;
			case Node.CDATA_SECTION_NODE:
				System.out.printf("%sCDATA[%s]\n", indent, substituteControlCodesWithReadables(nodeValue));
				break;
			case Node.PROCESSING_INSTRUCTION_NODE:
				System.out.printf("%sProcessingInstruction[name=%s, value=%s]\n"
								, indent, node.getNodeName(), substituteControlCodesWithReadables(nodeValue));
				break;
			default:
				System.out.printf("%s(Not yet to handle nodeType of '%s')\n", indent, node.getNodeType());
		}
	}

	private static String substituteControlCodesWithReadables(String str) {
		String retval;
		retval = StringUtils.replace(str   , "\n", "\\n");
		retval = StringUtils.replace(retval, "\t", "\\t");
		return retval;
	}
}
