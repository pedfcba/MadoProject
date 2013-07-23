package mado.xml;

import java.util.List;

import mado.ConfigInfo;
import mado.Tags;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseConfigNodes extends ParseNodes{
	private ConfigInfo config;
	
	public ParseConfigNodes()
	{
		list = parser.getNodes(Tags.configfile);
		config = new ConfigInfo();
		init();
	}
	
	private void init()
	{
		if(list != null && list.getLength() > 0)
		{
			Node effect = parser.getFirstTagNode(list, Tags.MAP);
			while(effect != null)
			{
				config.addMap(parser.getValueAttribute(effect));
				effect = parser.getNextTagNode(effect);
			}
			effect = parser.getFirstTagNode(list, Tags.PERSON);
			while(effect != null)
			{
				config.addPerson(parser.getValueAttribute(effect));
				effect = parser.getNextTagNode(effect);
			}
			effect = parser.getFirstTagNode(list, Tags.DIR);
			//绝对地址前缀
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
		if(parser.getValueAttribute(node).equals(name))
			return config.getDir() + node.getTextContent();
		node = parser.getFirstTagNode(list, Tags.MAP);
		while(node != null)
		{
			if(parser.getValueAttribute(node).equals(name))
				return config.getDir() + node.getTextContent();
			node = parser.getNextTagNode(node);
		}
		node = parser.getFirstTagNode(list, Tags.PERSON);
		while(node != null)
		{
			if(parser.getValueAttribute(node).equals(name))
				return config.getDir() + node.getTextContent();
			node = parser.getNextTagNode(node);
		}
		return null;
	}
	

	public void viewInfo() {
		// TODO Auto-generated method stub
		System.out.println("Dir:");
		System.out.println(this.config.getDir());
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
		System.out.println(cf.getAdress("阳台"));
		System.out.println(cf.getAdress("附窗子"));
	}

	@Override
	public NodeList parseNodes(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
