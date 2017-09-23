package model.Search;

//import java.util.ArrayList;
import model.Member;

public interface ISearchStrategy {
	
	//public ArrayList<Member> simpleSearch(ArrayList<Member> list);
	public boolean isMemberSelected(Member a_m);

}
