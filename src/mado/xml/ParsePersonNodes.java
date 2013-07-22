package mado.xml;

import org.w3c.dom.NodeList;

public class ParsePersonNodes extends ParseNodes {

	private PersonInfo personinfo;
	
	public ParsePersonNodes()
	{
		personinfo = new PersonInfo();
		parser = new XmlParser();
	}
	
	public NodeList parseNodes(String fileName) {
		// TODO Auto-generated method stub
		list = parser.getNodes(fileName);
		getInfo(list);
		return list;
	}
	
	//获取人物信息
	private void getInfo(NodeList list) {
		// TODO Auto-generated method stub
		personinfo.addEffect(parser.getFirstTagNode(list, "effects").getTextContent());
		personinfo.addName(parser.getFirstTagNode(list, "name").getTextContent());
		personinfo.addLocation(parser.getFirstTagNode(list, "location").getTextContent());
	}

	public void viewInfo() {
		// TODO Auto-generated method stub
		personinfo.viewInfo();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParsePersonNodes parse = new ParsePersonNodes();
		NodeList list = parse.parseNodes("src\\xmls\\Person_Madotsuki.xml");
		parse.personinfo.viewInfo();

	}

}
