package model.Search;

import model.Boat.BoatType;

public class SearchStrategy {

	public enum SimpleSearchMode{
		BY_NAME, BY_AGE_EQUAL_TO, BY_AGE_LESS_THAN, BY_AGE_GREATER_THAN, BY_MONTH, BY_BOAT_LENGTH, BY_BOAT_TYPE
	}
	
	public enum ComplexSearchMode{
		AND, OR
	}
	
	public ISearchStrategy getSearchByName(String name) {
		return new ByName(name);
	}

	public ISearchStrategy getSearchByAgeEqualTo(int age) {
		return new ByAgeEqualTo(age);
	}

	public ISearchStrategy getSearchByAgeGreaterThan(int age) {
		return new ByAgeGreaterThan(age);
	}

	public ISearchStrategy getSearchByAgeLessThan(int age) {
		return new ByAgeLessThan(age);
	}

	public ISearchStrategy getSearchByBoatLength(double length) {
		return new ByBoatLength(length);
	}

	public ISearchStrategy getSearchByBoatType(BoatType type) {
		return new ByBoatType(type);
	}

	public ISearchStrategy getSearchByMonth(int month) {
		return new ByMonth(month);
	}
}