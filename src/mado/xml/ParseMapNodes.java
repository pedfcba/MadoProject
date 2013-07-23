package mado.xml;

import mado.MapInfo;
import mado.Tags;

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
			if(link.getNodeName().equals(Tags.ITEM))
			{
				mapinfo.addItem(link.getTextContent());
			}
			else if(link.getNodeName().equals(Tags.EFFECT))
			{
				mapinfo.addEffect(link.getTextContent());
			}
			else if(link.getNodeName().equals(Tags.PERSON))
			{
				mapinfo.addPerson(link.getTextContent());
			}
			else if((link.getNodeName()).equals(Tags.LINK))
			{
				mapinfo.addLink(link.getTextContent());
			}
			else if((link.getNodeName()).equals(Tags.NAME))
			{
				mapinfo.addName(link.getTextContent());
			}
		}
	}

	public NodeList parseNodes(String filename) {
		// TODO Auto-generated method stub
		list = parser.getNodes(filename);
		mapinfo.clear();
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
	
	public MapInfo getMap()
	{
		return mapinfo;
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParseMapNodes map = new ParseMapNodes();
		map.parseNodes("src\\xmls\\Map_Home.xml");
		map.viewInfo();
	}

}
