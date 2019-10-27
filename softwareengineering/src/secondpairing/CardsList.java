package secondpairing;

import java.util.ArrayList;

public class CardsList {
	public static ArrayList<Cards> GetCards(String Json){
		ArrayList<Cards> list=new ArrayList<Cards>();
		String[] rex=Json.split(" ");
		for(int i=0;i<rex.length;i++)
		{
			Cards x=new Cards(rex[i]);
			list.add(x);
		}
		return list;
	}
	public static String toString(ArrayList<Cards> cards) {
		String out=new String();
		for(int i=0;i<cards.size();i++)
		{
			out=out+cards.get(i).toString();
			if (i<cards.size()-1)
				out=out+" ";
		}
		return out;
	}
}