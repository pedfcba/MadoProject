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
		//�ɴﳡ��������1��ѡ����һ�������ģ�����ѡȡ
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
		System.out.println("���");
		personinfo.viewInfo();
		System.out.println();
		System.out.println("������");
		mapinfo.viewInfo();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MadoParser mado = new MadoParser();
		mado.createPerson("������");
		System.out.println("�˶�ǰ��");
		System.out.println();
		mado.pickEffect();
		mado.viewInfo();
		mado.randMove();
		System.out.println();
		System.out.println();
		System.out.println("�˶���");
		System.out.println();
		mado.pickEffect();
		mado.viewInfo();
		mado.randMove();
		System.out.println();
		System.out.println("�ٴ��˶���");
		mado.pickEffect();
		mado.viewInfo();
		System.out.println();
		mado.randMove();
		System.out.println("��2���˶���");
		mado.pickEffect();
		mado.viewInfo();
	}
}
