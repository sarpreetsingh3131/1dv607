package model.Search;

import java.util.ArrayList;
import model.Member;

class ByAndStrategy implements ISearchStrategy {
	
	private ISearchStrategy m_leftSearch, m_rightSearch;
	
	public ByAndStrategy(ISearchStrategy a_leftSearch, ISearchStrategy a_rightSearch) {
	    m_leftSearch = a_leftSearch;
	    m_rightSearch = a_rightSearch;
	}
	
	/*
	@Override
	public ArrayList<Member> complexSearch(ArrayList<Member> firstList, ArrayList<Member> secondList) {
		ArrayList<Member> foundMembers = new ArrayList<Member>();
		for (Member m : firstList) {
			if (secondList.contains(m))
				foundMembers.add(m);
		}
		return foundMembers;
	}
	 */

	@Override
	public boolean isMemberSelected(Member a_m) {
		return m_leftSearch.isMemberSelected(a_m) && m_rightSearch.isMemberSelected(a_m);
	}
}
