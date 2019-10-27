package secondpairing;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.naming.LimitExceededException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import testGUI.view;
/*排序算法*/
public class SortCards {
	public static void SortByType(ArrayList<Cards> list) {
		Comparator<Cards> comparator=new Comparator<Cards>() {
			public int compare(Cards c1, Cards c2) {
				return c2.type.compareTo(c1.type);
			}
		};
		Collections.sort(list,comparator);
	}
	public static void SortByNum(ArrayList<Cards> list) {
		Comparator<Cards> comparator=new Comparator<Cards>() {
			public int compare(Cards c1, Cards c2) {
				return c1.num-c2.num;
			}
		};
		Collections.sort(list,comparator);
	}
	
	public static ArrayList<Cards> list;
	static OutCards result=new OutCards();
	static int M=5;
	static int N=0;
	static int[] a=new int[M];
	static int[] b=new int[M];
	static int[] front=new int[3];
	static int[] lasts=new int[5];
	static int[] middle=new int[5];
	static URLDemo Demo=new URLDemo();
	public static void C(ArrayList<Integer> xr,int m,int n){
		int i,j;
		for(i=n;i<=m;i++) {
			b[n-1] = i-1;
			if(n>1)
				C(xr,i-1,n-1);
			else {
				ArrayList<Integer> xr2=new ArrayList<>(xr);
				for(j=0;j<=M-1;j++)
				{
					int x=b[j]-j;
					lasts[j]=xr2.get(x);
					xr2.remove(x);
				}
				C2(xr2, 8, 5);
			}
		}
	}
	public static void C2(ArrayList<Integer> xr2,int m,int n){
		int i,j;
		for(i=n;i<=m;i++) {
			a[n-1] = i-1;
			if(n>1)
				C2(xr2,i-1,n-1);
			else {
				ArrayList<Integer> xr3=new ArrayList<>(xr2);
				for(j=0;j<=M-1;j++)
				{
					int x=a[j]-j;
					middle[j]=xr3.get(x);
					xr3.remove(x);
				}
				for(int k=0;k<3;k++)
					front[k]=xr3.get(k).intValue();
				OutCards test=Generateone();
				N++;
				test.GetWeight();
				if(test.Weight>result.Weight)
				{
					result=test;
				}
			}
		}
	}
	public static OutCards Generateone() {
		OutCards gen=new OutCards();
		for(int i=0;i<3;i++)
			gen.Front.add(new Cards(list.get(front[i]).toString()));
		for(int i=0;i<5;i++)
			gen.Middle.add(new Cards(list.get(middle[i]).toString()));
		for(int i=0;i<5;i++)
			gen.last.add(new Cards(list.get(lasts[i]).toString()));
		return gen;
	}
	public static void work() {
		long starTime=System.currentTimeMillis();
		int id;
		JSONObject GetCards=JSONObject.parseObject(Demo.sendPost("http://api.revth.com/game/open", null,0));//开局
		id=(JSONObject.parseObject(GetCards.get("data").toString())).getIntValue("id");
		String cards=null;
		cards=(JSONObject.parseObject(GetCards.get("data").toString())).getString("card");
//		view.receive_textField.setText(cards);
//		view.receive_textField.paintImmediately(view.receive_textField.getBounds());
		list =CardsList.GetCards(cards);
		ArrayList<Integer> xr=new ArrayList<Integer>();
		for(int i=0;i<=12;i++)
			xr.add(new Integer(i));
		C(xr, 13, 5);
		SortCards.SortByNum(list);
		
		JSONObject sendCard=new JSONObject(true);
		ArrayList<String> resualtCards=result.display();
		sendCard.put("id", id);
		sendCard.put("card", resualtCards);
		Demo.sendPost("http://api.revth.com/game/submit", sendCard, 1);
		System.out.println(sendCard.toString());
//		view.post_textField.setText(resualtCards.toString());
//		view.post_textField.paintImmediately(view.post_textField.getBounds());
		result=new OutCards();
		long endTime=System.currentTimeMillis();//定义一个结束时间
		long Time=endTime-starTime;//所需时间为结束时间-开始时间
		System.out.println("耗时："+Time+"毫秒");
	}
	public static void main(String arge[]) {
		/*JSONObject json=new JSONObject();
		Demo.Token="db687336-a35a-4d81-b1a1-ae119b3bc17a";
		for(int i=0;i<50;i++)
		{
			System.out.println("第"+(i+1)+"局开始:");
			work();
		}
		Demo.history(Demo, ""+39, 0);*/
		
		list =CardsList.GetCards("$2 $A *K *5 *7 $7 *10 $4 #6 *6 &7 #7 &5");
		ArrayList<Integer> xr=new ArrayList<Integer>();
		for(int i=0;i<=12;i++)
			xr.add(new Integer(i));
		C(xr, 13, 5);
		result.GetWeight();
		System.out.println(result.Front.toString());
		System.out.println(result.Middle.toString());
		System.out.println(result.last.toString());
		System.out.println(result.FrontWeight);
		System.out.println(result.MiddleWeight);
		System.out.println(result.lastWeight);
		SelectCards marks=new SelectCards();
		SortCards.SortByNum(result.last);
		SortCards.SortByNum(result.Middle);
		SortCards.SortByNum(result.Front);
		marks.AllDone(result.last);
		//marks.displayAll();
		
		
	}
	/*
	 * "$2 $A *K *5 *7 $7 *10 $4 #6 *6 &7 #7 &5"
	 * 
	 * "*2 &3 *8 *6 $5 $Q #K #9 *K *3 &4 $K $10"
	 * 
	 * "$2 $A *K *5 *7 $7 *10 $4 #6 *6 &7 #7 &5"
	 * */

}
