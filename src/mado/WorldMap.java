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
	
	//�趨�ٽӵĵ�ͼ
	private ArrayList<WorldMap> setLink(WorldMap mapName) {
		// TODO Auto-generated method stub
		linkedMap.add(mapName);
		return (ArrayList<WorldMap>)linkedMap;
	}

	//�趨��ͼ���ڵ�Effect
	private HashSet<String> setEffect(Effect eName) {
		// TODO Auto-generated method stub
		effect.add(eName.getEname());
		return (HashSet<String>)effect;
	}
	
	//�趨��ͼ���ڵ���
	private ArrayList<Person> setPerson(Person person) {
		// TODO Auto-generated method stub
		persons.add(person);
		return (ArrayList<Person>)persons;
	}
	
	
	
	/**
	 * �趨��ͼ���ڵ���Ʒ
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	//�趨��ͼ����
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
