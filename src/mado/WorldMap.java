package mado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mado.person.Person;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WorldMap {

	String mapName;
	Set<String> effect;
	List<WorldMap> linkedMap;
	List<Person> persons;
	List<Item> items;
	
	public WorldMap()
	{
		effect = new HashSet<String>();
		linkedMap = new ArrayList<WorldMap>();
		persons = new ArrayList<Person>();
		mapName = "";
	}
	
	public void initMap()
	{
		
	}
	
	//设定临接的地图
	private ArrayList<WorldMap> setLink(WorldMap mapName) {
		// TODO Auto-generated method stub
		linkedMap.add(mapName);
		return (ArrayList<WorldMap>)linkedMap;
	}

	//设定地图存在的Effect
	private HashSet<String> setEffect(Effect eName) {
		// TODO Auto-generated method stub
		effect.add(eName.getEname());
		return (HashSet<String>)effect;
	}
	
	//设定地图存在的人
	private ArrayList<Person> setPerson(Person person) {
		// TODO Auto-generated method stub
		persons.add(person);
		return (ArrayList<Person>)persons;
	}
	
	
	
	/**
	 * 设定地图存在的物品
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	//设定地图名称
	private void SetMap(String mName)
	{
		mapName = mName;
	}

	
	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @return the effect
	 */
	public Set<String> getEffect() {
		return effect;
	}

	/**
	 * @return the linkedMap
	 */
	public List<WorldMap> getLinkedMap() {
		return linkedMap;
	}

	/**
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
