package mado.xml;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AllEffect {
	private List<String> effects;
	public AllEffect()
	{
		effects = new LinkedList<String>();
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		XmlParser parser = new XmlParser();
		NodeList list = parser.getNodes("\\src\\xmls\\effects.xml");
		if(list.getLength() > 0)
		{
			Node effect = parser.getFirstTagNode(list, "effect");
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
	

}
