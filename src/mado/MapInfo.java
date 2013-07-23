package mado;

import java.util.LinkedList;
import java.util.List;

public class MapInfo {
	private String mapname;
	private List<String> linkinfo;
	private List<String> iteminfo;
	private List<String> personinfo;
	private List<String> effectinfo;

	public MapInfo()
	{
		mapname = "";
		linkinfo = new LinkedList<String>();
		iteminfo = new LinkedList<String>();
		personinfo = new LinkedList<String>();
		effectinfo = new LinkedList<String>();
	}

	public void addName(String nodeName) {
		// TODO Auto-generated method stub
		mapname = nodeName;
	}
	public void addItem(String nodeName) {
		// TODO Auto-generated method stub
		iteminfo.add(nodeName);
	}
	public void addEffect(String nodeName) {
		// TODO Auto-generated method stub
		effectinfo.add(nodeName);
	}
	public void addPerson(String nodeName) {
		// TODO Auto-generated method stub
		personinfo.add(nodeName);
	}
	public void addLink(String nodeName) {
		// TODO Auto-generated method stub
		linkinfo.add(nodeName);
	}

	public void viewInfo() {
		// TODO Auto-generated method stub
		System.out.println("Name:");
		System.out.println(mapname);
		System.out.println("Effects:");
		for(int i = 0; i < effectinfo.size(); i++)
			System.out.println(effectinfo.get(i));
		System.out.println("Items:");
		for(int i = 0; i < iteminfo.size(); i++)
			System.out.println(iteminfo.get(i));
		System.out.println("Links:");
		for(int i = 0; i < linkinfo.size(); i++)
			System.out.println(linkinfo.get(i));
		System.out.println("Persons:");
		for(int i = 0; i < personinfo.size(); i++)
			System.out.println(personinfo.get(i));
	}
	
	/**
	 * @return the mapname
	 */
	public String getMapname() {
		return mapname;
	}

	/**
	 * @return the linkinfo
	 */
	public List<String> getLinkinfo() {
		return linkinfo;
	}

	/**
	 * @return the iteminfo
	 */
	public List<String> getIteminfo() {
		return iteminfo;
	}

	/**
	 * @return the personinfo
	 */
	public List<String> getPersoninfo() {
		return personinfo;
	}

	/**
	 * @return the effectinfo
	 */
	public List<String> getEffectinfo() {
		return effectinfo;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapInfo map = new MapInfo();
		map.addLink("阳台");
		map.addPerson("附窗子");
		map.addEffect("菜刀");
		map.addItem("桌子");
		map.addName("自家");
		map.viewInfo();
		map.clear();
		map.addLink("阳台d");
		map.addPerson("附s窗子");
		map.addEffect("菜d刀");
		map.addItem("桌a子");
		map.addName("自f家");
		map.viewInfo();

	}

	public void clear() {
		// TODO Auto-generated method stub
		linkinfo.clear();
		personinfo.clear();
		effectinfo.clear();
		iteminfo.clear();
		mapname = "";
	}


}
