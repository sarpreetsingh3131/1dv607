package model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Registry {

	private ArrayList<Member> memberList;
	private ReadWriteFile readWriteFile;
	private int maxID = 0;
	
	
	public Registry(){
		memberList = new ArrayList<Member>();
	}

	public void readFiles() throws Exception{
		readWriteFile = new ReadWriteFile();
		memberList = readWriteFile.readFile();
		maxID = readWriteFile.getMaxID();
	}
	
	public ArrayList<Member> getMemberList() {
		return new ArrayList<Member>(memberList);
	}

	public void registerMember(String name, String personalNumber) throws ParseException {
		this.memberList.add(new Member(name, personalNumber, ++maxID)); //incrementing maxID generates a new unique member ID.
	}
	
	/**
	 * @param inMember
	 * @throws ParseException 
	 */
	public void updateMember(Member inMember, String name, String personalNumber) throws ParseException {
			inMember.setName(name);
			inMember.setPersonalnumber(personalNumber);
	}

	public void deleteMember(Member m) {
		this.memberList.remove(m);
	}

	/**
	 * The method is used in view side to select a member based on console input
	 * @param ID of member in integer form
	 * @return a member from present member list.
	 * 			null if member does not exist
	 */
	public Member lookUpMember(int ID) {
		for (Member m : this.memberList) {
			if (m.getMemberID() == ID)
				return m;
		}
		return null;
	}
	
	/**
	 * Saves the current data into file.
	 * @throws IOException 
	 */
	public void saveRegistry() throws IOException {
		//This maxID in argument is current maximum value of user's ID. This is because userID is generated based on increment of maxID.
		readWriteFile.writeFile(memberList, maxID);
	}
}