package model.Search;

//import java.util.ArrayList;
import model.Member;

class ByMonth implements ISearchStrategy {

	private int month;

	public ByMonth(int month) {
		this.month = month;
	}

	/*
	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : list) {
			if (m.getBirthMonth() == month) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
	*/
	
	@Override
	public boolean isMemberSelected(Member a_m) {
		return a_m.getBirthMonth() == month;
	}
}
