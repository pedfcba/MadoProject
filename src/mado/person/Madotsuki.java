package mado.person;

import mado.Effect;
import mado.WorldMap;

public class Madotsuki extends Person {
	//����״̬
	boolean sleeping = false;

	public Madotsuki()
	{
		this.setName("������");
		this.setLocation("�Լ�");
	}

	@Override
	public String personInfo() {
		// TODO Auto-generated method stub
		System.out.println(getName());
		System.out.println(getLocation());
		viewEffects();

		String info = "���" + getName()+ " λ�ã�";
		info += getLocation() + " \nEffect��";
		int count = 0;
		if(!effects.isEmpty())
		{
			for(; count < effects.size()-1; count++)
				info += effects.get(count) + " ";
			info += effects.get(count);
		}
		return info;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Madotsuki mado = new Madotsuki();
		mado.personInfo();
		System.out.println(mado.personInfo());
		mado.pickEffect("book");
		System.out.println(mado.personInfo());
		mado.pickEffect("book2");
		mado.personInfo();
		//mado.move();
		System.out.println(mado.personInfo());

	}




}
