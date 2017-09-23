package model.Search;

//import java.util.ArrayList;
import model.Boat;
import model.Member;

class ByBoatLength implements ISearchStrategy {

	private double length;

	public ByBoatLength(double length) {
		this.length = length;
	}

	/*
	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : list) {
			for (Boat b : m.getBoatList()) {
				if (b.getLength() == length) {
					foundMembers.add(m);
					break;
				}
			}
		}
		return foundMembers;
	}
	*/


	@Override
	public boolean isMemberSelected(Member a_m) {
		for (Boat b : a_m.getBoatList()) {
			if (b.getLength() == length) {
				return true;
			}
		}
		return false;
	}
}
