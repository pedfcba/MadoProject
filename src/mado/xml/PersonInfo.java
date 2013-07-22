package mado.xml;

public class PersonInfo {
	
	//名字
	private String personname;
	//持有道具
	private int effects = 0;
	//位置
	private String location;
	private String priorLocation;

	public PersonInfo()
	{
		personname = "";
		location = "";
		priorLocation = "";
	}
	
	public PersonInfo(String pname) {
		// TODO Auto-generated constructor stub
		personname = pname;
		location = "";
		priorLocation = "";
	}

	public void viewInfo()
	{
		System.out.println("name: ");
		System.out.println(personname);
		System.out.println("effects: ");
		System.out.println(effects);
		System.out.println("now at: ");
		System.out.println(location);
		System.out.println("before at：");
		System.out.println(priorLocation);
	}

	public void addName(String nodeName) {
		// TODO Auto-generated method stub
		personname = nodeName;
	}
	
	public void addEffect(String nodeName) {
		// TODO Auto-generated method stub
		//****
		//读取effects.xml文件
		//****
		effects = 0;
	}
	
	public void addLocation(String locat)
	{
		location = locat;
		if(priorLocation.length() <= 0)
		{
			priorLocation = locat;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}
	
}
