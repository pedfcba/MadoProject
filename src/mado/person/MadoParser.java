package mado.person;

import java.util.List;
import java.util.Random;

import mado.AllEffect;
import mado.Effect;
import mado.MapInfo;
import mado.PersonInfo;
import mado.xml.ParseConfigNodes;
import mado.xml.ParseMapNodes;
import mado.xml.ParsePersonNodes;

public class MadoParser{
	PersonInfo personinfo;
	MapInfo mapinfo;
	ParseConfigNodes config;
	ParseMapNodes map;
	ParsePersonNodes person;
	SpeakBehavior speak;

	
	public void wakeUp()
	{
		if(!personinfo.isWaking())
		{
			personinfo.wakUp();
			person.refreshXml();
		}
	}
	
	public MadoParser()
	{
		config = new ParseConfigNodes();
		personinfo = new PersonInfo();
		mapinfo = new MapInfo();
		map = new ParseMapNodes();
		person = new ParsePersonNodes();
	}

	public void createPerson(String name)
	{
		person.parseNodes(config.getAdress(name));
		personinfo = person.getPerson();
		map.parseNodes(config.getAdress(personinfo.getLocation()));
		mapinfo = map.getMap();
	}
	
	public MapInfo changeMap(String name)
	{
		mapinfo.clear();
		map.parseNodes(config.getAdress(name));
		return map.getMap();
	}
	

	public void randMove()
	{
		Random rand = new Random();
		int r = rand.nextInt(mapinfo.getLinkinfo().size());
		//可达场景数大于1且选中上一个场景的，重新选取
		while(mapinfo.getLinkinfo().size() > 1 && mapinfo.getLinkinfo().get(r).equals(personinfo.getPriorLocation()))
			r = rand.nextInt(mapinfo.getLinkinfo().size());
		personinfo.moveLocation(mapinfo.getLinkinfo().get(r));
		mapinfo = changeMap(personinfo.getLocation());
		person.refreshXml();
	}
	
	public void pickEffect()
	{
		List<String> effects = mapinfo.getEffectinfo();
		for(int i = 0; i < effects.size(); i++)
			personinfo.addEffect(effects.get(i));
		person.refreshXml();
	}

	
	public void viewInfo()
	{
		System.out.println("人物：");
		personinfo.viewInfo();
		System.out.println();
		System.out.println("场景：");
		mapinfo.viewInfo();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MadoParser mado = new MadoParser();
		mado.createPerson("附窗子");
		System.out.println("运动前：");
		System.out.println();
		mado.pickEffect();
		mado.viewInfo();
		mado.randMove();
		System.out.println();
		System.out.println();
		System.out.println("运动后：");
		System.out.println();
		mado.pickEffect();
		mado.viewInfo();
		mado.randMove();
		System.out.println();
		System.out.println("再次运动后：");
		mado.pickEffect();
		mado.viewInfo();
		System.out.println();
		mado.randMove();
		System.out.println("再2次运动后：");
		mado.pickEffect();
		mado.viewInfo();
	}
}
