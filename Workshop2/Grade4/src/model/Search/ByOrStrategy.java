package model.Search;

//import java.util.ArrayList;
import model.Member;

class ByOrStrategy implements ISearchStrategy {

	private ISearchStrategy m_leftSearch, m_rightSearch;
	
	public ByOrStrategy(ISearchStrategy a_leftSearch, ISearchStrategy a_rightSearch) {
	    m_leftSearch = a_leftSearch;
	    m_rightSearch = a_rightSearch;
	}
	/*
	@Override
	public ArrayList<Member> complexSearch(ArrayList<Member> firstList, ArrayList<Member> secondList) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		foundMembers.addAll(firstList);
		for (Member m : secondList) {
			if (!foundMembers.contains(m))
				foundMembers.add(m);
		}
		return foundMembers;
	}
	*/
	@Override
	public boolean isMemberSelected(Member a_m) {
		return m_leftSearch.isMemberSelected(a_m) || m_rightSearch.isMemberSelected(a_m);
	}
}
