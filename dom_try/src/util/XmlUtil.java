package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {
	
	private static final int NUM_INDENT = 4;
	private static final String UNIT_INDENT = StringUtils.repeat(" ", NUM_INDENT);

	public static String toPrettyString(Document doc) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(traceTree(doc.getDocumentElement()));
		
		return sb.toString();
	}
	
	private static String traceTree(Node node) {
		return traceTree(node, 0);
	}
	
	private static final String RE_WHITE_SPACES = "\\A\\s*\\z";
	private static final Pattern PATTERN_WHITE_SPACES = Pattern.compile(RE_WHITE_SPACES);

	private static String traceTree(Node node, int indentLevel) {
		final String indent = StringUtils.repeat(UNIT_INDENT, indentLevel);

		String value = node.getNodeValue();
		
		switch (node.getNodeType()) {
			case Node.ELEMENT_NODE:
				NamedNodeMap attrs = node.getAttributes();
				List<String> strAttrs = new ArrayList<String>();
				for (int i = 0, size = attrs.getLength(); i < size; i++) {
					Attr attr = (Attr)attrs.item(i);
					strAttrs.add(String.format("%s=\"%s\"", attr.getNodeName(), attr.getNodeValue()));
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append(String.format("%s<%s", indent, node.getNodeName()));
				if (strAttrs.size() > 0) {
					sb.append(' ');
					sb.append(StringUtils.join(strAttrs, ' '));
				}
				sb.append(">\n");
				
				NodeList childNodes = node.getChildNodes();
				for (int i = 0, size = childNodes.getLength(); i < size; i++) {
					sb.append(traceTree(childNodes.item(i), indentLevel + 1));
				}
				
				sb.append(String.format("%s</%s>\n", indent, node.getNodeName()));
				
				return sb.toString();
			case Node.TEXT_NODE:
				if (PATTERN_WHITE_SPACES.matcher(value).matches()) {
					return "";
				}
				sb = new StringBuilder();
				sb.append(indent);
				sb.append("");
				sb.append(value);
				sb.append("\n");
				return sb.toString();
			case Node.CDATA_SECTION_NODE:
				return String.format("%s<[CDATA[%s]>\n", indent, value);
			case Node.COMMENT_NODE:
				return String.format("%s<!--%s-->\n", indent, value);
			default:
				return String.format("%s(Not yet to handle node type of '%s')\n", indent, node.getNodeType());
		}
	}
	
	
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(args[0]);
			
			System.out.println(XmlUtil.toPrettyString(doc));
		} catch (ParserConfigurationException | IOException | SAXException e) {
			System.err.println(e);
		}
	}
}
