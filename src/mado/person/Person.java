package mado.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import mado.Effect;
import mado.WorldMap;
import mado.xml.PersonInfo;

public abstract class Person 
{
	//台词
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
	
	//移动场景
	public String move()
	{
		//从当前地图的关联地图选出下一个要移动的地点
		priorLocation =  location;
		return getRandMap(location);
	}
	
	
	protected String getRandMap(String location) {
		// TODO Auto-generated method stub
		
		return null;
	}
	//命名
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
		//从输入的数字得到物品列表
		

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

	//获取名字
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	//获得道具
	public boolean pickEffect(String effect)
	{
		if(!effects.contains(effect))
		{
			effects.getInfo(effect);
			return true;
		}
		return false;
	}
	
	
	//查看个人信息
	public abstract String personInfo();
	
	//查看当前所有Effects
	protected void viewEffects() {
		// TODO Auto-generated method stub
		if(effects.isEmpty())
			System.out.println("还没有Effect哦");
		else
			System.out.println(effects);
	}
}
