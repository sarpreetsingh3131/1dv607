package model.Search;

//import java.util.ArrayList;
import model.Member;

class ByAgeGreaterThan extends ByAgeEqualTo {

	public ByAgeGreaterThan(int age) {
		super(age);
	}

	/*
	@Override
	public ArrayList<Member> simpleSearch(ArrayList<Member> list) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : list) {
			if (m.getAge() > age) {
				foundMembers.add(m);
			}
		}
		return foundMembers;
	}
	*/
	@Override
	public boolean isMemberSelected(Member a_m) {
		return a_m.getAge() > age;
	}
}
