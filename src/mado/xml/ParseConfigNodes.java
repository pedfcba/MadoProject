package mado.xml;

import java.util.List;

import javax.swing.text.html.HTML.Tag;


import mado.object.ConfigInfo;
import mado.object.Tags;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseConfigNodes extends ParseNodes{
	private static ConfigInfo config;
	
	public ParseConfigNodes()
	{
		config = new ConfigInfo();
		init();
	}
	
	public XmlParser getParser()
	{
		return parser;
	}
	
	private void init()
	{
		list = parser.getNodes(System.getProperty("user.dir") + Tags.configfile);
		if(list != null && list.getLength() > 0)
		{
			Node effect = parser.getFirstTagNode(list, Tags.MAP);
			while(effect != null)
			{
				config.addMap(parser.getTagAttribute(effect, Tags.VALUE));
				effect = parser.getNextTagNode(effect);
			}
			effect = parser.getFirstTagNode(list, Tags.PERSON);
			while(effect != null)
			{
				config.addPerson(parser.getTagAttribute(effect, Tags.VALUE));
				effect = parser.getNextTagNode(effect);
			}
			effect = parser.getFirstTagNode(list, Tags.DIR);
			//��Ե�ַǰ׺
			String dir = System.getProperty("user.dir") + effect.getTextContent();
			config.addDir(dir);
		}
	}
	
	public List<String> getMaps()
	{
		return config.getMaps();
	}
	
	public List<String> getPersons()
	{
		return config.getPersons();
	}
	
	public String getDir()
	{
		return config.getDir();
	}
	
	public String getAdress(String name)
	{
		Node node = parser.getFirstTagNode(list, Tags.EFFECTS);
		if(node.getNodeName().equals(name))
			return config.getDir() + node.getTextContent();
		node = parser.getFirstTagNode(list, Tags.CLASSES);
		if(node.getNodeName().equals(name))
			return config.getDir() + node.getTextContent();
		node = parser.getFirstTagNode(list, Tags.MAP);
		while(node != null)
		{
			if(parser.getTagAttribute(node, Tags.VALUE).equals(name))
				return config.getDir() + node.getTextContent();
			node = parser.getNextTagNode(node);
		}
		node = parser.getFirstTagNode(list, Tags.PERSON);
		while(node != null)
		{
			if(parser.getTagAttribute(node, Tags.VALUE).equals(name))
				return config.getDir() + node.getTextContent();
			node = parser.getNextTagNode(node);
		}
		return null;
	}
	

	public void viewInfo() {
		// TODO Auto-generated method stub
		System.out.println("Dir:");
		System.out.println(config.getDir());
		System.out.println("Maps:");
		for(int i = 0; i < config.getMaps().size(); i++)
			System.out.println(config.getMaps().get(i));
		System.out.println("Persons:");
		for(int i = 0; i < config.getPersons().size(); i++)
			System.out.println(config.getPersons().get(i));
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParseConfigNodes cf = new ParseConfigNodes();
		cf.viewInfo();
	}

}
