package mado;

public class Effect {
	private int evalue;
	public Effect()
	{
		setEvalue(0);
	}
	
	public Effect(String name, int value) {
		// TODO Auto-generated constructor stub
		setEvalue(value);
	}

	//设置当前道具的值
	public void setEvalue(int value) {
		evalue = value;
	}

	//获取当前道具的值
	public int getEvalue() {
		return evalue;
	}
}
