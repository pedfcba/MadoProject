package mado;

import java.util.LinkedList;
import java.util.List;

import mado.xml.ParseConfigNodes;
import mado.xml.XmlParser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AllEffect {
	private List<String> effects;
	private ParseConfigNodes config;
	public AllEffect()
	{
		config = new ParseConfigNodes();
		effects = new LinkedList<String>();
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		XmlParser parser = new XmlParser();
		NodeList list = parser.getNodes(config.getAdress(Tags.EFFECTS));
		if(list.getLength() > 0)
		{
			Node effect = parser.getFirstTagNode(list, Tags.EFFECT);
			while(effect != null)
			{
				effects.add(effect.getTextContent());
				effect = parser.getNextTagNode(effect);
			}
		}
	}
	
	public List<String> getEffects()
	{
		return effects;
	}
	
	public void viewInfo() {
		// TODO Auto-generated method stub
		System.out.println("Effects:");
		for(int i = 0; i < effects.size(); i++)
			System.out.println(effects.get(i));
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AllEffect al = new AllEffect();
		al.viewInfo();
	}
	

}
