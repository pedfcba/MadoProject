package mado.xml;


import java.util.List;

import mado.object.MapInfo;
import mado.object.Tags;

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


	//判断是否探索过该物品或人或地图
	public boolean isVisited(String name, String tag)
	{
		if(tag.equals(Tags.ITEM) || tag.equals(Tags.PERSON))
		{
			Node target = parser.getFirstTagNode(list, tag);
			while(target != null)
			{
				if(target.getTextContent().equals(name) && parser.getTagAttribute(target, Tags.VISITED).equals(Tags.visited))
				{
					return true;
				}
				target = parser.getNextTagNode(target);
			}
		}
		else
		{
			ParseConfigNodes config = new ParseConfigNodes();
			Node target = config.getParser().getFirstTagNode(config.getNodeList(), tag);
			while(target != null)
			{
				if(config.getParser().getTagAttribute(target, Tags.VALUE).equals(name) && config.getParser().getTagAttribute(target, Tags.VISITED).equals(Tags.visited))
				{
					return true;
				}
				target = config.getParser().getNextTagNode(target);
			}
		}
		return false;
	}

	//设置为已探索
	public void setVisited(String name, String tag)
	{
		if(tag.equals(Tags.MAP))
		{
			ParseConfigNodes config = new ParseConfigNodes();
			Node target = config.getParser().getFirstTagNode(config.getNodeList(), tag);
			while(target != null)
			{
				if(target.getAttributes().getNamedItem(Tags.VALUE).getTextContent().equals(name)
						&& !parser.getTagAttribute(target, Tags.VISITED).equals(Tags.visited))
				{
					target.getAttributes().getNamedItem(Tags.VISITED).setTextContent(Tags.visited);
					config.getParser().refreshXml();
					break;
				}
				target = config.getParser().getNextTagNode(target);
			}
		}
		else
		{
			Node target = parser.getFirstTagNode(list, tag);
			while(target != null)
			{
				if(target.getTextContent().equals(name) 
						&& !parser.getTagAttribute(target, Tags.VISITED).equals(Tags.visited))
				{
					parser.setTagAttribute(name, tag, Tags.VISITED, Tags.visited);
					
					break;
				}
				target = parser.getNextTagNode(target);
			}
		}
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParseMapNodes map = new ParseMapNodes();
		map.parseNodes("src\\xmls\\Map_Home.xml");
		map.setVisited(Tags.BLOCKWORLD, Tags.MAP);
		map.viewInfo();
	}

}
