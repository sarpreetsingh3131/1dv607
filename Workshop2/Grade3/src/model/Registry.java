package model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import model.Search.ISearchStrategy;

public class Registry {

	private ArrayList<Member> memberList;
	private ReadWriteFile readWriteFile;
	private int maxID = 0;

	public Registry() {
		memberList = new ArrayList<Member>();
	}

	public void readFiles() throws Exception {
		readWriteFile = new ReadWriteFile();
		memberList = readWriteFile.readFile();
		maxID = readWriteFile.getMaxID();
	}

	public ArrayList<Member> getMemberList() {
		return new ArrayList<Member>(memberList);
	}

	public void registerMember(String name, String personalNumber) throws ParseException {
		// Incrementing maxID generates a new unique ID
		this.memberList.add(new Member(name, personalNumber, ++maxID));
	}

	public void updateMember(Member inMember, String name, String personalNumber) throws ParseException {
		inMember.setName(name);
		inMember.setPersonalnumber(personalNumber);
	}

	public void deleteMember(Member m) {
		this.memberList.remove(m);
	}

	public Member lookUpMember(int ID) {
		for (Member m : this.memberList) {
			if (m.getMemberID() == ID)
				return m;
		}
		return null;
	}
	public ArrayList<Member> search(ISearchStrategy a_search) {
		ArrayList<Member> ret = new ArrayList<Member>();
		for (Member m : this.memberList) {
			if (a_search.isMemberSelected(m)){
	    		ret.add(m);
	    	}
		}
		return ret;
	}
	public void saveRegistry() throws IOException {
		// This maxID in argument is current maximum value of user's ID. This is
		// because userID is generated based on increment of maxID.
		readWriteFile.writeFile(memberList, maxID);
	}
}