package view;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;

public class Console implements IView {

	private String input;
	private Scanner scan = new Scanner(System.in);
	private Registry registry;
	private final String quitSequence = "q";
	private final String returnSequence = "r";

	/**
	 * Constructor of console. Reads member data and boat file
	 */
	public Console() {
		registry = new Registry();
		try {
			registry.readFiles();
		} catch (Exception e) {
			displayError("CANNOT READ THE FILES. PLEASE CHECK THE CONTENT OF \'Member.txt\' AND \'Boat.txt\' FILE.");
		}

	}

	@Override
	public void startProgram() {
		displayWelcomeMessage();
		displayMainInstructions();
	}

	@Override
	public void displayWelcomeMessage() {
		System.out.println("***************************************");
		System.out.println("* WELCOME TO MEMBER REGISTERY PROGRAM *");
		System.out.println("***************************************\n");
	}

	@Override
	public void displayMainInstructions() {
		while (input != quitSequence) {
			System.out.println("\nSELECT THE OPTION");
			System.out.println("1: DISPLAY COMPACT LIST");
			System.out.println("2: DISPLAY VERBOSE LIST");
			System.out.println("3: CREATE A MEMBER");
			System.out.print(quitSequence + ": SAVE & QUIT\n>");

			input = scan.next();
			switch (input) {
			case ("1"):
				displayCompactList(registry.getMemberList());
				displayMemberInstructions();
				break;
			case ("2"):
				displayVerboseList(registry.getMemberList());
				displayMemberInstructions();
				break;
			case ("3"):
				registerMember(getMemberNameFromUser(), getMemberPersonalnumberFromUser());
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				displayError("INVALID OPTION");
				break;
			}
		}
	}

	@Override
	public void displayCompactList(ArrayList<Member> m) {
		if (m.isEmpty()) {
			displayError("LIST IS EMPTY");
			displayMainInstructions();
			return;
		}
		System.out.println("+ ID |  NAME OF A MEMBER  | TOTAL BOATS +");
		for (Member member : m)
			System.out.printf("%5d|%20s|%13d|\n", member.getMemberID(), member.getName(), member.getNumberOfBoats());
		System.out.println("+----|--------------------|-------------+");
	}

	@Override
	public void displayVerboseList(ArrayList<Member> m) {
		if (m.isEmpty()) {
			displayError("LIST IS EMPTY");
			displayMainInstructions();
			return;
		}
		for (Member member : m)
			displaySelectedMember(member);
	}

	@Override
	public void registerMember(String name, String personalnumber) {
		try {
			registry.registerMember(name, personalnumber);
			displaySuccess("MEMBER CREATED SUCCESSFULLY !!");
		} catch (ParseException e) {
			displayError("INCORRECT PERSONAL NUMBER DATE FORMAT");
		}
	}

	@Override
	public void updateMember(Member m, String name, String personalnumber) {
		try {
			registry.updateMember(m, name, personalnumber);
			displaySuccess("MEMBER UPDATED SUCCESSFULLY !!");
		} catch (ParseException e) {
			displayError("INCORRECT PERSONAL NUMBER DATE FORMAT");
		}
	}

	@Override
	public void deleteMember(Member m) {
		registry.deleteMember(m);
		displaySuccess("MEMBER DELETED SUCCESSFULLY !!");
	}

	@Override
	public void displaySelectedMember(Member m) {
		System.out.println("\nMEMBER ID: " + m.getMemberID());
		System.out.printf("NAME:%s (PNR.%s) HAS %d BOATS\n", m.getName(), m.getPersonalnumber(), m.getNumberOfBoats());
		if (m.getNumberOfBoats() > 0) {
			System.out.println("+ # |  BOAT TYPE  | LENGTH (m) +");
			int i = 0;
			for (Boat b : m.getBoatList())
				System.out.printf("%4d|%13s|%6.2f      |\n", ++i, b.getType(), b.getLength());
			System.out.printf("+---|-------------|------------+\n");
		}
	}

	@Override
	public void registerBoat(Member m, double boatLength, BoatType boattype) {
		try {
			m.registerBoat(boatLength, boattype);
			displaySuccess("BOAT REGISTERD SUCCESSFULLY !!");
		} catch (Exception e) {
			displayError("UNABLE TO REGISTER A BOAT. LENGHT IS INCORRECT");
		}
	}

	@Override
	public void updateBoat(Member m, double length, BoatType type, Boat boat) {
		try {
			m.updateBoat(length, type, boat);
			displaySuccess("BOAT UPDATED SUCCESSFULLY !!");
		} catch (Exception e) {
			displayError("UNABLE TO REGISTER A BOAT. LENGHT IS INCORRECT");
		}
	}

	@Override
	public void deleteBoat(Member m, Boat b) {
		m.deleteBoat(b);
		displaySuccess("BOAT DELETED SUCCESSFULLY !!");
	}

	@Override
	public void displayError(String error) {
		System.out.println("ERROR: <<< " + error + " >>>");
	}

	@Override
	public void displaySuccess(String success) {
		System.out.println("*** " + success + " ***");
	}

	@Override
	public void quitProgram() {
		try {
			registry.saveRegistry();
			scan.close();
			displaySuccess("REGISTRY SAVED SUCCESSFULLY!!.");
			System.exit(1);
		} catch (IOException e) {
			displayError("ERROR!! REGISTRY FILE IS NOT SAVED.");
		}
	}

	/***************** CONSOLE NAVIGATION *********************************/
	private void displayMemberInstructions() {
		if (registry.getMemberList().isEmpty())
			return;
		while (input != quitSequence || input != returnSequence) {
			System.out.println("\nSELECT THE OPTION");
			System.out.println("1: CREATE A MEMBER");
			System.out.println("2: UPDATE A MEMBER");
			System.out.println("3: DELETE A MEMBER");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": SAVE & QUIT\n>");
			
			Member member;
			input = scan.next();
			
			switch (input) {
			case ("1"):
				registerMember(getMemberNameFromUser(), getMemberPersonalnumberFromUser());
				displayCompactList(registry.getMemberList());
				break;
			case ("2"):
				member = getMemberBasedOnMemberIDGivenByUser();
				displaySelectedMember(member);
				displayUpdateMemberInstructions(member);
				break;
			case ("3"):
				member = getMemberBasedOnMemberIDGivenByUser();
				deleteMember(member);
				displayCompactList(registry.getMemberList());
				break;
			case (returnSequence):
				displayMainInstructions();
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				displayError("INVALID OPTION");
				break;
			}
		}
	}

	private void displayUpdateMemberInstructions(Member member) {
		while (input != quitSequence) {
			System.out.println("\nSELECT THE OPTION");
			System.out.println("1: UPDATE NAME");
			System.out.println("2: UPDATE PERSONAL NUMBER");
			System.out.println("3: UPDATE NAME & PERSONAL NUMBER");
			System.out.println("4: REGISTER A BOAT");
			System.out.println("5: UPDATE A BOAT");
			System.out.println("6: DELETE A BOAT");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": SAVE & QUIT\n>");
			
			Boat boat;
			input = scan.next();
			
			// After each operations we display member's information again to show the changes.
			switch (input) {
			case ("1"):
				updateMember(member, getMemberNameFromUser(), member.getPersonalnumber());
				displaySelectedMember(member);
				break;
			case ("2"):
				updateMember(member, member.getName(), getMemberPersonalnumberFromUser());
				displaySelectedMember(member);
				break;
			case ("3"):
				updateMember(member, getMemberNameFromUser(), getMemberPersonalnumberFromUser());
				displaySelectedMember(member);
				break;
			case ("4"):
				registerBoat(member, getBoatLengthFromUser(), getBoatTypeFromUser());
				displaySelectedMember(member);
				break;
			case ("5"):
				boat = getBoatFromUser(member);
				displayUpdateBoatInstructions(member, boat);
				break;
			case ("6"):
				boat = getBoatFromUser(member);
				deleteBoat(member, boat);
				displaySelectedMember(member);
				break;
			case (returnSequence):
				displayCompactList(registry.getMemberList());
				displayMemberInstructions();
				break;
			case (quitSequence):
				quitProgram();
				break;
			default:
				displayError("INVALID OPTION");
				break;
			}
		}
	}

	private void displayUpdateBoatInstructions(Member member, Boat boat) {
		System.out.println("\nSELECT THE OPTION");
		System.out.println("1: UPDATE LENGTH");
		System.out.println("2: UPDATE BOAT TYPE");
		System.out.println("3: UPDATE LENGTH & BOAT TYPE");
		System.out.println(returnSequence + ": RETURN");
		System.out.print(quitSequence + ": SAVE & QUIT\n>");
		
		input = scan.next();
	
		switch (input) {
		case ("1"):
			updateBoat(member, getBoatLengthFromUser(), boat.getType(), boat);
			displaySelectedMember(member);
			break;
		case ("2"):
			updateBoat(member, boat.getLength(), getBoatTypeFromUser(), boat);
			displaySelectedMember(member);
			break;
		case ("3"):
			updateBoat(member, getBoatLengthFromUser(), getBoatTypeFromUser(), boat);
			displaySelectedMember(member);
			break;
		case (returnSequence):
			displaySelectedMember(member);
			break;
		case (quitSequence):
			quitProgram();
			break;
		default:
			displayError("INVALID OPTION");
			break;
		}
	}

	/**************** CONSOLE INPUT DATA METHODS ************/

	private String getMemberNameFromUser() {
		System.out.print("NAME\n>");
		input = scan.next() + scan.nextLine();
		while (!checkName(input)) {
			displayError("INCORRECT NAME!! PLEASE WRITE AGAIN (Eg: John Smith)");
			input = scan.next() + scan.nextLine();
		}
		return input;
	}

	private String getMemberPersonalnumberFromUser() {
		System.out.print("PERSONAL NUMBER (YYYYMMDDXXXX)\n>");
		input = scan.next();
		while (input.length() != 12) {
			displayError("INCORRECT PERSONAL NUMBER!! PERSONAL NUMBER MUST HAVE 12 DIGITS");
			input = scan.next();
		}
		return input;
	}

	private Member getMemberBasedOnMemberIDGivenByUser() {
		if (registry.getMemberList().isEmpty()) {
			displayError("LIST IS EMPTY");
			displayMemberInstructions();
		}
		displayCompactList(registry.getMemberList());
		System.out.print("PLEASE TYPE THE MEMBER ID\n>");
		input = scan.next();
		
		while (!checkMemberID(input)) {
			displayError("INVALID MEMBER ID!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		return registry.lookUpMember(Integer.parseInt(input));
	}

	private double getBoatLengthFromUser() {
		System.out.print("LENGTH(m)\n>");
		input = scan.next();
		while (!checkBoatLength(input)) {
			displayError("INCORRECT LENGTH!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
			return Double.parseDouble(input);
	}

	private BoatType getBoatTypeFromUser() {
		// Prints out types of boats
		System.out.println("BOAT TYPE");
		System.out.println("+ ID | Boat type +");
		for (BoatType b : BoatType.values())
			System.out.printf("%5d|%11s|\n", b.getCode(), b.toString());
		System.out.println("+----|-----------+");

		// receives an input
		System.out.print("\nENTER BOAT TYPE ID\n>");
		input = scan.next();
		while (!checkIndex(input, BoatType.values().length)) {
			displayError("INVALID BOAT TYPE ID!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
			return BoatType.values()[Integer.parseInt(input) - 1]; // array
	}

	private Boat getBoatFromUser(Member member) {
		if (member.getNumberOfBoats() == 0) {
			displayError("THIS MEMBER HAVE NO BOAT CURRENTLY");
			displaySelectedMember(member);
			displayUpdateMemberInstructions(member);
			return null;
		}
		System.out.print("PLEASE ENTER THE BOAT #\n>");
		input = scan.next();
		while (!checkIndex(input, member.getNumberOfBoats())) {
			displayError("INVALID BOAT #!! PLEASE WRITE AGAIN");
			input = scan.next();
		}
		return member.getBoatList().get(Integer.parseInt(input) - 1);
	}

	/****************** HELPER METHODS FOR CORRECT INPUT *********/
	/**
	 * 
	 * @param name
	 * @return true if name is only consists of alphabetic letters and white
	 *         spaces.
	 */
	private boolean checkName(String name) {
		boolean charexists = false;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (Character.isAlphabetic(c))
				charexists = true;
			if (!Character.isAlphabetic(c) && !Character.isWhitespace(c))
				return false;
		}
		return charexists;
	}

	/**
	 * 
	 * @param input
	 * @return true if member id exists in registry
	 */
	private boolean checkMemberID(String input) {
		try {
			return registry.lookUpMember(Integer.parseInt(input)) != null;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param input
	 * @param size
	 * @return true if input lies within the index of boat types
	 */
	private boolean checkIndex(String input, int size) {
		try {
			return Integer.parseInt(input) > 0 && Integer.parseInt(input) <= size;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean checkBoatLength(String input){
		try {
			Double.parseDouble(input);
		} catch (Exception e) {
			return false;
		}	
		return true;
	}
}
