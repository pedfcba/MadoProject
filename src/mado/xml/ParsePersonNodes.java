package mado.xml;

import mado.PersonInfo;
import mado.Tags;

import org.w3c.dom.NodeList;

public class ParsePersonNodes extends ParseNodes {

	private PersonInfo personinfo;
	private String filepath;
	
	public ParsePersonNodes()
	{
		personinfo = new PersonInfo();
	}
	
	public NodeList parseNodes(String fileName) {
		// TODO Auto-generated method stub
		filepath = fileName;
		list = parser.getNodes(fileName);
		setInfo(list);
		return list;
	}
	
	//获取人物信息
	private void setInfo(NodeList list) {
		// TODO Auto-generated method stub
		personinfo.setEffect(Integer.parseInt(parser.getFirstTagNode(list, Tags.EFFECTS).getTextContent()));
		personinfo.setName(parser.getFirstTagNode(list, Tags.NAME).getTextContent());
		personinfo.moveLocation(parser.getFirstTagNode(list, Tags.LOCATION).getTextContent());
	}

	public void viewInfo() {
		// TODO Auto-generated method stub
		personinfo.viewInfo();
	}
	
	public PersonInfo getPerson()
	{
		return personinfo;
	}
	
	public String getFilepath()
	{
		return filepath;
	}
	
	public void refreshXml()
	{
		String location = personinfo.getLocation();
		parser.setTagContext(location, Tags.LOCATION);
		String effects = Integer.toString(personinfo.getEffects());
		parser.setTagContext(effects, Tags.EFFECTS);
		parser.refreshXml();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParsePersonNodes parse = new ParsePersonNodes();
		parse.parseNodes("src\\xmls\\Person_Madotsuki.xml");
		parse.personinfo.viewInfo();

	}

}
