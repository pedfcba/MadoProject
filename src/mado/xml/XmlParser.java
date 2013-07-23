package mado.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser{
	protected Document document;
	private String filepath;

	public XmlParser()
	{
		init();
	}
	private void init()
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

	public NodeList getNodes(final String fileName) {
		// TODO Auto-generated method stub
		NodeList nodes = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(fileName);
			filepath = fileName;
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

	public Node getFirstTagNode(NodeList list, final String tag)
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
		if(node.getAttributes().getLength() > 0)
			return node.getAttributes().getNamedItem("value").getNodeValue();
		else
			return null;
	}

	public void refreshXml()
	{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = tf.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filepath));

			t.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void setTagContext(String value, String tag) {
		// TODO Auto-generated method stub
		if(document.getElementsByTagName(tag) != null)
		{
			document.getElementsByTagName(tag).item(0).setTextContent(value);
			refreshXml();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XmlParser ps = new XmlParser();
		//ps.readNodes(ps.getNodes("src\\xmls\\Map_Home.xml"));
		NodeList list = ps.getNodes("src\\xmls\\Map_Home.xml");
		Node root = ps.getFirstRealNode(list);
		List<String> items = new LinkedList<String>();
		items = ps.getChildTags(root, "item");
		Random rand = new Random();
		int i = rand.nextInt(items.size());
		System.out.println(items.get(i));
		System.out.println();
		System.out.println("ok?");
	}
}