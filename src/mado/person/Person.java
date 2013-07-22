package mado.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import mado.Effect;
import mado.WorldMap;
import mado.xml.PersonInfo;

public abstract class Person 
{
	//̨��
	SpeakBehavior speak;
	PersonInfo person;
	
	public Person()
	{
		person = new PersonInfo();
	}
	public Person(String pname)
	{
		person = new PersonInfo(pname);
	}
	public Person(String pname, int peffects)
	{
		name = pname;
		effectsInPack = peffects;
		effects = new ArrayList<String>();
		location = "";
		priorLocation = "";
	}
	
	//�ƶ�����
	public String move()
	{
		//�ӵ�ǰ��ͼ�Ĺ�����ͼѡ����һ��Ҫ�ƶ��ĵص�
		priorLocation =  location;
		return getRandMap(location);
	}
	
	
	protected String getRandMap(String location) {
		// TODO Auto-generated method stub
		
		return null;
	}
	//����
	protected void setName(String pname)
	{
		name = pname;
	}
		
	
	/**
	 * @param effectsInPack the effectsInPack to set
	 */
	protected void setItems(int items) {
		this.effectsInPack = items;
	}
	/**
	 * 
	 * @param effects the effects to set
	 */
	protected void setEffects(int effects) {
		//����������ֵõ���Ʒ�б�
		

	}
	/**
	 * @param location the location to set
	 */
	protected void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the effectsInPack
	 */
	public int getItems() {
		return effectsInPack;
	}
	/**
	 * @return the effects
	 */
	public List<String> getEffects() {
		return effects;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	//��ȡ����
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	//��õ���
	public boolean pickEffect(String effect)
	{
		if(!effects.contains(effect))
		{
			effects.getInfo(effect);
			return true;
		}
		return false;
	}
	
	
	//�鿴������Ϣ
	public abstract String personInfo();
	
	//�鿴��ǰ����Effects
	protected void viewEffects() {
		// TODO Auto-generated method stub
		if(effects.isEmpty())
			System.out.println("��û��EffectŶ");
		else
			System.out.println(effects);
	}
}
