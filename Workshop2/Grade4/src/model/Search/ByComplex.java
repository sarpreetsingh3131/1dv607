/**
 * 
 */
package model.Search;

import java.util.ArrayList;

import model.Member;

/**
 * @author songhokun
 *
 */
public class ByComplex implements ISearchStrategy{

	private ArrayList<ISearchStrategy> childSearch = new ArrayList<ISearchStrategy>();
	private ArrayList<SearchStrategy.ComplexSearchMode> childOperation = new ArrayList<SearchStrategy.ComplexSearchMode>();
	
	@Override
	public boolean isMemberSelected(Member a_m) {
		boolean result;
		
		//left hand of the search needs to be done first.
		result = childSearch.get(0).isMemberSelected(a_m);
		
		for(int i = 1; i< childSearch.size();i++){
			if(childOperation.get(i) == SearchStrategy.ComplexSearchMode.AND){
				result = result && childSearch.get(i).isMemberSelected(a_m);
			}
			else{
				result = result || childSearch.get(i).isMemberSelected(a_m);
			}
		}
		return result;
	}
	public void add(ISearchStrategy criteria, SearchStrategy.ComplexSearchMode operation){
		childSearch.add(criteria);
		childOperation.add(operation);
	}
	public void remove(ISearchStrategy criteria){
		childOperation.remove(childSearch.indexOf(criteria));
		childSearch.remove(criteria);
	}

}
