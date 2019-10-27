package secondpairing;

import java.util.ArrayList;
/*识别牌型*/
public class SelectCards {
	public double SameTypeContinuityNum;			//同花顺O
	public double FourSameCards;					//炸弹O
	public double ThreeSameCardsOnePair;			//葫芦  可计算
	public double SameType;							//同花 O
	public double ContinuityNum;					//顺子O
	public double ThreeSameCards;					//三条O
	public double TwoPair;							//两对O
	public double Pair;								//对子O
	public double None;								//散牌X
	
	public SelectCards() {
		SameTypeContinuityNum=0;
		FourSameCards=0;ThreeSameCardsOnePair=0;SameType=0;
		ContinuityNum=0;ThreeSameCards=0;TwoPair=0;Pair=0;None=0;
	}
	public void init() {
		SameTypeContinuityNum=0;
		FourSameCards=0;ThreeSameCardsOnePair=0;SameType=0;
		ContinuityNum=0;ThreeSameCards=0;TwoPair=0;Pair=0;None=0;
	}
	public void AllDone(ArrayList<Cards> cards) {
		SortCards.SortByNum(cards);
		Continuity(cards);
		SetAboutNum(cards);
		if(cards.size()==5)
			SameType(cards);
		if(JUDGENULL())
		{
			None=cards.get(cards.size()-1).num/10.0;
			return;
		}
		if(SameType>0&&ContinuityNum>0)
		{
			SameTypeContinuityNum=80;
			SameType=0;
			ContinuityNum=0;
		}
	}
	boolean JUDGENULL() {
		if (SameTypeContinuityNum!=0)
			return false;
		else if(FourSameCards!=0)
			return false;
		else if(ThreeSameCardsOnePair!=0)
			return false;
		else if(SameType!=0)
			return false;
		else if(ContinuityNum!=0)
			return false;
		else if(ThreeSameCards!=0)
			return false;
		else if(TwoPair!=0)
			return false;
		else if(Pair!=0)
			return false;
		return true;
	}
	
	public double Weight() {
		return SameTypeContinuityNum+FourSameCards+ThreeSameCardsOnePair+SameType+ContinuityNum+ThreeSameCards+TwoPair+Pair+None;
	}
	public void Continuity(ArrayList<Cards> cards){//顺子
		for(int i=0;i<cards.size()-4;i++)
		{
			if((cards.get(i).num==cards.get(i+1).num-1)&&(cards.get(i+1).num-1==cards.get(i+2).num-2)&&(cards.get(i+2).num-2==cards.get(i+3).num-3)&&(cards.get(i+3).num-3==cards.get(i+4).num-4))
			{
				ContinuityNum=40+(double)cards.get(i+4).num/10;
			}
		}
	}
	
	public void SameType(ArrayList<Cards> cards) {//判断同花
		if(((cards.get(0).type).equals(cards.get(1).type))&&((cards.get(0).type).equals(cards.get(2).type))&&((cards.get(0).type).equals(cards.get(3).type))&&((cards.get(0).type).equals(cards.get(4).type)))
		{
			SameType=50+(double)cards.get(4).num/10+(double)cards.get(3).num/100+(double)cards.get(2).num/1000+(double)cards.get(1).num/10000+(double)cards.get(0).num/100000;
		}
	}
	public void SetAboutNum(ArrayList<Cards> cards) {//判断对子、三条、炸弹、葫芦
		double[] Four=new double[5];
		double[] Three=new double[5];
		int j=0;
		double[] pair=new double[5];
		int k=0;
		double Tmark=0;
		for(int i=0;i<cards.size()-1;i++)
		{
			if(cards.get(i).num==cards.get(i+1).num) {
				Pair=Pair+10;
				pair[i]=cards.get(i).num;
				if(i<(cards.size()-2)) {
					if(cards.get(i+1).num==cards.get(i+2).num){
						ThreeSameCards=30;
						Three[i]=cards.get(i+2).num;
						j=i;
						if (i<(cards.size()-3)) {
							if(cards.get(i+2).num==cards.get(i+3).num)
								FourSameCards=70;
						Four[i]=cards.get(i+3).num;
						k=i;
						}
					}
				}
			}
		}/*权值需要改进*/
		if(Four[k]>0)
		{
			Pair=0;
			ThreeSameCards=0;
			FourSameCards=FourSameCards+Four[k]/10;
			return;
		}
		else if(Three[j]>0)
		{
			pair[j]=0;
			pair[j+1]=0;
			Pair=Pair-20;
			if(pair[0]>0||pair[1]>0||pair[2]>0||pair[3]>0||pair[4]>0)
			{
				ThreeSameCardsOnePair=60+Three[j]/10;
				ThreeSameCards=0;
				Pair=0;
				return;
			}
			else
			{
				ThreeSameCards=ThreeSameCards+Three[j]/10;
				return;
			}
		}
		if(Pair==20)
		{
			int p=1;
			TwoPair=20;
			for(int i=4;i>=0;i--)
			{
				if (pair[i]!=0) {
					TwoPair=TwoPair+pair[i]/(10.0*p);
					p=p*10;
					if(pair[i]+1==Tmark)
						TwoPair=TwoPair+1.5;
					Tmark=pair[i];
				}
			}
			Pair=0;
		}
		else if(Pair==10)
		{
			Pair=Pair+pair[0]/10+pair[1]/10+pair[2]/10+pair[3]/10+pair[4]/10;
		}
	}
}
