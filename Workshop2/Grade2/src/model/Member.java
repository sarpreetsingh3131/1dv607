package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Boat.BoatType;

/**
 * Information class of a member object.
 */
public class Member {

	private String name;
	private String personalnumber;
	private int memberID;
	private ArrayList<Boat> boatList = new ArrayList<Boat>();

	public Member(String name, String personalnumber, int memberID) throws ParseException {
		setPersonalnumber(personalnumber);
		this.name = name;
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public String getPersonalnumber() {
		return personalnumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPersonalnumber(String personalnumber) throws ParseException {
		String pn = personalnumber.substring(0, 8);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setLenient(false);
		df.parse(pn);
		this.personalnumber = personalnumber;
	}

	public int getNumberOfBoats() {
		return boatList.size();
	}

	public ArrayList<Boat> getBoatList() {
		return new ArrayList<Boat>(boatList);
	}

	public int getMemberID() {
		return this.memberID;
	}

	public void registerBoat(double length, BoatType type) {
		this.boatList.add(new Boat(length, type));
	}

	public void updateBoat(double length, BoatType type, Boat boat) {
			boat.setLength(length);
			boat.setType(type);
	}

	public void deleteBoat(Boat boat) {
		this.boatList.remove(boat);
	}
}