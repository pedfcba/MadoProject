package mado.xml;

import java.util.LinkedList;
import java.util.List;

import mado.AllEffect;

public class ParseEffectNodes extends XmlParser{
	private AllEffect effects;
	private List<String> list;
	
	public ParseEffectNodes()
	{
		effects = new AllEffect();
		list = effects.getEffects();
	}
	
	public int getNum(String effect)
	{
		return encode(list.indexOf(effect));
	}
	
	private int encode(int num)
	{
		return 1 << num;
	}
	
	public List<String> getEffectList(int num)
	{
		List<String> target = new LinkedList<String>();
		int index = 0;
		while(num % 2 != 0 || num/2 != 0)
		{
			if(num % 2 == 1)
			{
				target.add(list.get(index));
			}
			index++;
			num /= 2;
		}
		return target;
	}
	
	public boolean haveEffect(int effects, String input)
	{
		int target = list.indexOf(input);
		if((target & effects) == 0)
			return false;
		else
			return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParseEffectNodes p = new ParseEffectNodes();
		System.out.println(p.getNum("ºìÂÌµÆ"));
		System.out.println(p.getEffectList(223));

	}

}
