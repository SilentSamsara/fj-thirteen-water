package secondpairing;

public class Cards {
	public String type;
	public int num;
	public int ChangeToInt (String x) {
		int z=-1;
		if(x.length()==2)
			z=10;
		else
		{
			char y=x.charAt(0);
			if(y>='2'&&y<='9')
				z=y-'0';
			else {
				if(y=='A')
					z=14;
				else if(y=='J')
					z=11;
				else if(y=='Q')
					z=12;
				else
					z=13;
			}
		}
		return z;
	}
	public Cards(String x) {
		type=x.substring(0,1);
		x=x.replaceFirst("\\"+type, "");
		num=ChangeToInt(x);
	}
	public String toString() {
		int z=num;
		char y=(char)(z+'0');
		if(y>='2'&&y<='9')
			z=y;
		else if(y==(10+'0'))
			return type+""+"10";
		else {
			if(z==14)
				z='A';
			else if(z==11)
				z='J';
			else if(z==12)
				z='Q';
			else
				z='K';
		}
		return type+""+(char)z;
	}
}