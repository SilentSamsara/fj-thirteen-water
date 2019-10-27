package secondpairing;
/*存储牌型*/
import java.util.ArrayList;

public class OutCards {
	public ArrayList<Cards> Front;
	double FrontWeight;
	public ArrayList<Cards> Middle;
	double MiddleWeight;
	public ArrayList<Cards> last;
	double lastWeight;
	public double Weight;
	public OutCards() {
		Front=new ArrayList<Cards>();
		Middle=new ArrayList<Cards>();
		last=new ArrayList<Cards>();
		Weight=0;
	}
	public ArrayList<String> display() {
		ArrayList<String> output=new ArrayList<>();
		output.add(CardsList.toString(Front));
		output.add(CardsList.toString(Middle));
		output.add(CardsList.toString(last));
		return output;
	}
	public void GetWeight() {
		SelectCards marks=new SelectCards();
		SortCards.SortByNum(last);
		SortCards.SortByNum(Middle);
		SortCards.SortByNum(Front);
		marks.AllDone(last);
		lastWeight=marks.Weight();
		marks.init();
		marks.AllDone(Middle);
		MiddleWeight=marks.Weight();
		marks.init();
		marks.AllDone(Front);
		FrontWeight=marks.Weight();
		marks.init();
		Weight=lastWeight+MiddleWeight+FrontWeight;//三墩权值相加
		if(FrontWeight>MiddleWeight)
			Weight=0;
		if(FrontWeight>lastWeight)
			Weight=0;
		if(MiddleWeight>lastWeight)
			Weight=0;
	}
}
