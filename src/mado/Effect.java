package mado;

import java.util.List;

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
	
	public void viewInfo() {
		// TODO Auto-generated method stub
		System.out.println("Effects:");
		AllEffect ef = new AllEffect();
		List<String> list = ef.getEffects();
		int index = 0, temp = evalue;
		while(temp % 2 != 0 || temp/2 != 0)
		{
			if(temp % 2 == 1)
				System.out.println(list.get(index));
			index++;
			temp /= 2;
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Effect ef = new Effect();
		ef.setEvalue(100);
		ef.viewInfo();
		System.out.println(ef.getEvalue());
	}
}
