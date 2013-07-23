

public class Item {
	//道具名
	private String iname;
	//道具值
	private int ivalue;
	public Item()
	{
		setIname("");
		setIvalue(0);
	}
	public Item(String name, int value) {
		// TODO Auto-generated constructor stub
		setIname(name);
		setIvalue(value);
	}
	
	//设置当前道具名
	public void setIname(String name)
	{
		iname = name;
	}
	//获取当前道具名
	public String getIname() {
		// TODO Auto-generated method stub
		return iname;
	}

	//设置当前道具的值
	public void setIvalue(int value) {
		ivalue = value;
	}

	//获取当前道具的值
	public int getEvalue() {
		return ivalue;
	}
	
	
}
