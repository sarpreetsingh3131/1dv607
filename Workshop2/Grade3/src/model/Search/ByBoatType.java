package model.Search;

//import java.util.ArrayList;
import model.Boat;
import model.Boat.BoatType;
import model.Member;

class ByBoatType implements ISearchStrategy {

	private BoatType type;

	public ByBoatType(BoatType type) {
		this.type = type;
	}

	/*
	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : list) {
			for (Boat b : m.getBoatList()) {
				if (b.getType() == type) {
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
			if (b.getType() == type) {
				return true;
			}
		}
		
		return false;
	}
}
