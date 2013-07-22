package mado.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseMapNodes extends ParseNodes {

	private MapInfo mapinfo;

	public ParseMapNodes()
	{
		mapinfo = new MapInfo();
	}

	private void add(Node node)
	{
		NodeList links = node.getChildNodes();
		for(int mcount = 0; mcount < links.getLength(); mcount++)
		{
			Node link = links.item(mcount);
			if(link.hasChildNodes())
				add(link);
			if(link.getNodeName().equals("item"))
			{
				mapinfo.addItem(link.getTextContent());
			}
			else if(link.getNodeName().equals("effect"))
			{
				mapinfo.addEffect(link.getTextContent());
			}
			else if(link.getNodeName().equals("person"))
			{
				mapinfo.addPerson(link.getTextContent());
			}
			else if((link.getNodeName()).equals("link"))
			{
				mapinfo.addLink(link.getTextContent());
			}
			else if((link.getNodeName()).equals("name"))
			{
				mapinfo.addName(link.getTextContent());
			}
		}
	}

	public NodeList parseNodes(String filename) {
		// TODO Auto-generated method stub
		list = parser.getNodes(filename);
		for(int i = 0; i < list.getLength(); i++)
		{
			Node child = list.item(i);
			add(child);
		}
		return list;
		//		mapinfo.output();
	}

	public void viewInfo() {
		// TODO Auto-generated method stub
		mapinfo.viewInfo();
	}

}
