package mado.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser{
	protected Document document;
	
	public XmlParser()
	{
		init();
	}
	public void init()
	{
		try 
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory
			.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

	public NodeList getNodes(String fileName) {
		// TODO Auto-generated method stub
		NodeList nodes = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(fileName);
			nodes = document.getChildNodes();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} 
		catch (SAXException e) {
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return nodes;
	}

	/*	public void readNodes(NodeList list)
	{
		for(int i = 0; i < list.getLength(); i++)
		{
			System.out.println(i + ": ");
			Node x = list.item(i);
			System.out.println("getLocalName：");
			System.out.println(x.getLocalName());
			System.out.println("getNamespaceURI：");
			System.out.println(x.getNamespaceURI());
			System.out.println("NodeName:");
			System.out.println(x.getNodeName());
			System.out.println("NodeValue:");
			System.out.println(x.getNodeValue());
			System.out.println("TextContent");
			System.out.println(x.getTextContent());
			NodeList child = x.getChildNodes();
			System.out.println("lengthhhhhhhhhhh");
			System.out.println(child.getLength());
			for(int j = 0; j < child.getLength(); j++ )
			{
				Node cnode = child.item(j);
				System.out.println(j + " -");
				System.out.println("getLocalName：");
				System.out.println(cnode.getLocalName());
				System.out.println("getNamespaceURI：");
				System.out.println(cnode.getNamespaceURI());
				System.out.println("NodeName:");
				System.out.println(cnode.getNodeName());
				System.out.println("NodeValue:");
				System.out.println(cnode.getNodeValue());
				System.out.println("TextContent");
				System.out.println(cnode.getTextContent());
			}
		}
			list = x.getChildNodes();
		x = list.item(3);
		System.out.println();
		System.out.println("next level：");
		System.out.println("getLocalName：");
		System.out.println(x.getLocalName());
		System.out.println("getNamespaceURI：");
		System.out.println(x.getNamespaceURI());
		System.out.println("NodeName:");
		System.out.println(x.getNodeName());
		System.out.println("NodeValue:");
		System.out.println(x.getNodeValue());
		for (int i = 0; i < list.getLength(); i++) {
			Node employee = list.item(i);
			NodeList info = employee.getChildNodes();
			for (int j = 0; j < info.getLength(); j++) {
				Node node = info.item(j);
				NodeList employeeMeta = node.getChildNodes();
				for (int k = 0; k < employeeMeta.getLength(); k++) {
					System.out.println(employeeMeta.item(k).getNodeName()
							+ ":" + employeeMeta.item(k).getTextContent());
				}
			}
		}
		parseNodes = new ParseMapNodes();
		parseNodes.parseNode(list);
		Node test = getFirstRealNode(list);

		NodeList test2 = test.getChildNodes();
		test = getFirstRealNode(test2);
		while(test != null)
		{
			System.out.println(test.getNodeName());
			test = getNextRealNode(test);
		}
	}

	 */

	//获取第一个真实的子结点
	public Node getFirstRealNode(NodeList list)
	{
		Node target = null;
		for (int i = 0; i < list.getLength(); i++)
		{
			Node node = list.item(i);
			if(node.getNodeName().equals("#text"))
				continue;
			else
			{
				target = node;
				break;
			}
		}
		return target;
	}

	//获取下一个含标签的结点
	public Node getNextRealSibling(Node node)
	{
		Node target = node.getNextSibling();
		while(target != null)
		{
			if(!target.getNodeName().equals("#text"))
				break;
			target = target.getNextSibling();
		}
		return target;
	}

	//获取node子结点下tag标签的内容列表
	public List<String> getChildTags(Node node, String tag)
	{
		List<String> list = new LinkedList<String>();
		if(node.hasChildNodes())
		{
			NodeList childs = node.getChildNodes();
			Node child = getFirstTagNode(childs, tag);
			while(child != null)
			{
				list.add(child.getTextContent());
				child = getNextTagNode(child);
			}
		}
		return list;
	}

	public Node getFirstRealChild(Node node)
	{
		if(node.hasChildNodes())
		{
			NodeList childs = node.getChildNodes();
			Node child = getFirstRealNode(childs);
			return child;
		}
		return null;
	}

	public Node getFirstTagNode(NodeList list, String tag)
	{
		Node child = getFirstRealNode(list);
		Node target = child;
		while(child != null)
		{
			target = child;
			if(target.getNodeName().equals(tag))
				return target;
			else if(target.hasChildNodes())
			{
				target = getFirstTagNode(target.getChildNodes(), tag);
				if (target != null)
					break;
			}
			child = getNextRealSibling(child);
		}
		return target;
	}

	public Node getNextTagNode(Node node)
	{
		Node target = getNextRealSibling(node);
		while(target != null)
		{
			if(node.getNodeName().equals(target.getNodeName()))
				return target;
			target = getNextRealSibling(target);
		}
		return target;
	}
	
	public String getTagValue(Node node, String vname)
	{
		return node.getAttributes().getNamedItem(vname).getNodeValue();
	}
	
	public String getValueAttribute(Node node)
	{
		return node.getAttributes().getNamedItem("value").getNodeValue();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XmlParser ps = new XmlParser();
		//ps.readNodes(ps.getNodes("src\\xmls\\Map_Home.xml"));
		NodeList list = ps.getNodes("src\\xmls\\Map_Home.xml");
		Node root = ps.getFirstRealNode(list);
		Node child = ps.getFirstRealNode(root.getChildNodes());
		List<String> items = new LinkedList<String>();
		items = ps.getChildTags(root, "item");
		Random rand = new Random();
		int i = rand.nextInt(items.size());
		System.out.println(items.get(i));
		System.out.println();
		System.out.println("ok?");
		Node item = ps.getFirstTagNode(list, "item");
		while(item != null)
		{
			System.out.println(item.getNodeName());
			System.out.println(ps.getValueAttribute(item));
			System.out.println(item.getTextContent());
			item = ps.getNextTagNode(item);
		}
	}
}