package mado;

import java.util.LinkedList;
import java.util.List;

public class ConfigInfo {
	private String dir;
	private List<String> maps;
	private List<String> persons;
	
	public ConfigInfo()
	{
		maps = new LinkedList<String>();
		persons = new LinkedList<String>();
	}

	public void addMap(String valueAttribute) {
		// TODO Auto-generated method stub
		maps.add(valueAttribute);
	}

	public void addPerson(String valueAttribute) {
		// TODO Auto-generated method stub
		persons.add(valueAttribute);
	}

	public List<String> getMaps() {
		// TODO Auto-generated method stub
		return maps;
	}

	public List<String> getPersons() {
		// TODO Auto-generated method stub
		return persons;
	}

	public String getDir() {
		// TODO Auto-generated method stub
		return dir;
	}

	public void addDir(String adir) {
		// TODO Auto-generated method stub
		dir = adir;
	}
	
	

}
