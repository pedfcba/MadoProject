package mado.xml;

import org.w3c.dom.NodeList;

public abstract class ParseNodes {
	protected NodeList list;
	protected XmlParser parser;
	public ParseNodes()
	{
		parser = new XmlParser();
	}
	
	public abstract NodeList parseNodes(String filename);
	public abstract void viewInfo();
}
