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

	//���õ�ǰ���ߵ�ֵ
	public void setEvalue(int value) {
		evalue = value;
	}

	//��ȡ��ǰ���ߵ�ֵ
	public int getEvalue() {
		return evalue;
	}
}
