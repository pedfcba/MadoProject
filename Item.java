

public class Item {
	//������
	private String iname;
	//����ֵ
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
	
	//���õ�ǰ������
	public void setIname(String name)
	{
		iname = name;
	}
	//��ȡ��ǰ������
	public String getIname() {
		// TODO Auto-generated method stub
		return iname;
	}

	//���õ�ǰ���ߵ�ֵ
	public void setIvalue(int value) {
		ivalue = value;
	}

	//��ȡ��ǰ���ߵ�ֵ
	public int getEvalue() {
		return ivalue;
	}
	
	
}
