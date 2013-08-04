package mado.xml;

import org.w3c.dom.NodeList;

public abstract class ParseNodes{
	protected NodeList list;
	protected XmlParser parser;
	public ParseNodes()
	{
		parser = new XmlParser();
	}
	
	public XmlParser getParser()
	{
		return parser;
	}
	
	public NodeList getNodeList()
	{
		return list;
	}
	
	public abstract void viewInfo();
}
