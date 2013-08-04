package mado.person;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mado.object.Effect;
import mado.object.Tags;
import mado.speak.NothingSpeakBehavior;
import mado.speak.SpeakBehavior;
import mado.xml.ParseClassNodes;
import mado.xml.ParseMapNodes;
import mado.xml.ParsePersonNodes;


public class MadoSpeak{

	private List<String> items;
	private List<String> persons;
	private static SpeakBehavior speak;
	private Random rand = new Random();
	private int limit;

	public MadoSpeak()
	{
		items = new LinkedList<String>();
		persons = new LinkedList<String>();
		speak = new NothingSpeakBehavior();
		limit = 10;
	}

	public void setSpeakBehavior(SpeakBehavior speakbehavior)
	{
		speak = speakbehavior;
	}

	public void setSpeakBehavior(String name)
	{
		ParseClassNodes parser = new ParseClassNodes();
		String classname = parser.getClassName(name);
		try {
			speak = (SpeakBehavior)Class.forName(classname).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String speak(ParseMapNodes map, ParsePersonNodes person) {
		// TODO Auto-generated method stub
		items = map.getMap().getIteminfo();
		persons = map.getMap().getPersoninfo();

		LinkedList<String> list = new LinkedList<String>();
		list.add(Tags.NOTHING);
		if(items.size() > 0)
			list.addAll(items);
		if(persons.size() > 0)
			list.addAll(persons);
		if(map.getMap().getEffectinfo().size() > 0)
			list.addAll(map.getMap().getEffectinfo());
		if(!list.isEmpty())
		{
			limit = 10 + list.size()*5;
		}
		
		int r = rand.nextInt(limit);
		//捏脸
		if (r == 0)
		{
			setSpeakBehavior(Tags.PinchFace);
		}
		//移动
		else if(r >= 1 && r < 9)
		{
			if(!map.getMap().getLinkinfo().isEmpty())
			{
				setSpeakBehavior(Tags.MOVE);
			}
			else
				setSpeakBehavior(Tags.NOTHING);
		}
		//调查
		else
		{
			//将所有人物、物品、effect添加到list后随机选择
			if(!list.isEmpty())
			{
				int num = rand.nextInt(list.size());
				String sentence = list.get(num);
				while(person.getPerson().haveEffect(sentence))
				{
					sentence = list.get(rand.nextInt(list.size()));
				}
				//增加使用床的几率
				if(list.contains(Tags.REALBED))
				{
					if(rand.nextInt(100) > 50)
						sentence = Tags.REALBED;
				}
				else if(list.contains(Tags.DOOR))
				{
					if(rand.nextInt(100) > 50)
						sentence = Tags.DOOR;
				}
				//System.out.println(sentence);
				//String sentence = "��Ϸ��";
				//setSpeakBehavior(new RealNasuSpeakBehavior());
				setSpeakBehavior(sentence);
			}
			else
			{
				setSpeakBehavior(Tags.NOTHING);
			}
			//两次探索
			if(list.isEmpty())
				limit -= (30 + list.size())/2;
		}
		System.out.println("choosed:" + r);
		System.out.println(limit);
		return speak.speak(map, person);
	}
}
