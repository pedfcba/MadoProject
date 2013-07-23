package mado;

import mado.xml.ParseConfigNodes;
import mado.xml.ParseEffectNodes;
import mado.xml.ParseMapNodes;


public class PersonInfo {
	//����״̬
	private boolean awake;
	//����
	private String personname;
	//���е���
	private int effects = 0;
	//λ��
	private String location;
	private String priorLocation;

	public PersonInfo()
	{
		awake = false;
		personname = "";
		location = "";
		priorLocation = "";
	}
	
	public PersonInfo(String pname) {
		// TODO Auto-generated constructor stub
		awake = false;
		personname = pname;
		location = "";
		priorLocation = "";
	}
	
	public boolean isWaking()
	{
		return awake;
	}
	
	public void viewInfo()
	{
		System.out.println("name: ");
		System.out.println(personname);
		System.out.println("effects: ");
		ParseEffectNodes parse = new ParseEffectNodes();
		System.out.println(parse.getEffectList(effects));
		System.out.println("now at: ");
		System.out.println(location);
		System.out.println("before at��");
		System.out.println(priorLocation);
	}

	public void setName(String nodeName) {
		// TODO Auto-generated method stub
		personname = nodeName;
	}
	
	public String getName()
	{
		return personname;
	}
	
	public void addEffect(String nodeName) {
		// TODO Auto-generated method stub
		//��ȡeffects.xml�ļ�
		ParseEffectNodes efs = new ParseEffectNodes();
		//��򣬽���Ʒ������ӵ�effects��
		effects |= efs.getNum(nodeName);
	}
	
	public void addEffect(int peffects) {
		// TODO Auto-generated method stub
		effects |= peffects;
	}
	
	public void setEffect(int effect)
	{
		effects = effect;
	}
	public int getEffects()
	{
		return effects;
	}
	
	//�ƶ�����λ��
	public void moveLocation(String locat)
	{
		priorLocation = location;
		location = locat;
	}
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}
	public String getPriorLocation()
	{
		return priorLocation;
	}
	
	public void wakUp() {
		// TODO Auto-generated method stub
		awake = true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}


	
}
