package mado.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MadoParser extends XmlParser{
	XmlParser xmlparser;
	ParseNodes nodeparser;
	NodeList root;
	
	public MadoParser()
	{
		xmlparser = new XmlParser();
		nodeparser = new ParseMapNodes();
	}
	
	public void parseFile(String filename)
	{
		root = nodeparser.parseNodes(filename);
	}
	
	public void setParseType(ParseNodes parse)
	{
		nodeparser = parse;
	}

	public String getAdress(String value)
	{
		Node target = xmlparser.getFirstTagNode(root, "link");
		while(target != null)
		{
			if(target.getTextContent().equals(value))
				return xmlparser.getValueAttribute(target);
			target = xmlparser.getNextTagNode(target);
		}
		return null;
	}

}
