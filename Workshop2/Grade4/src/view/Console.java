package view;

import java.io.IOException;
import java.text.ParseException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import model.Authentication;
import model.Boat;
import model.Boat.BoatType;
import model.Member;
import model.Registry;
import model.Search.ISearchStrategy;
import model.Search.SearchStrategy;
import model.Search.SearchStrategy.ComplexSearchMode;
import model.Search.SearchStrategy.SimpleSearchMode;

public class Console implements IView {

	private String input;
	private Scanner scan = new Scanner(System.in);
	private Registry registry;
	private final String quitSequence = "q";
	private final String returnSequence = "r";
	private Authentication authentication;

	/**
	 * Constructor of console. Reads member data and boat file
	 */
	public Console() {
		registry = new Registry();
		authentication = new Authentication();
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
			System.out.println("4: SIMPLE SEARCH");
			System.out.println("5: COMPLEX SEARCH");
			if (!authentication.isLoggedIn())
				System.out.println("6: LOG IN");
			System.out.print(quitSequence + ": SAVE & QUIT\n>");

			input = scan.next();
			if (!authentication.isLoggedIn() && input.equals("3")) {
				displayError("ACCESS DENIED!! PLEASE LOG IN");
				displayMainInstructions();
			}

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
			case ("4"):
				ArrayList<Member> m = registry.search(doSimpleSearch());
				displaySearchResult(m);
				break;
			case ("5"):
				doComplexSearch();
				break;
			case ("6"):
				if (!authentication.isLoggedIn())
					logIn(getUsernameFromUser(), getPasswordFromUser());
				else
					displayError("INVALID OPTION");
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
		for (Member member : m) {
			displaySelectedMember(member);
		}
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
			System.exit(0);
		} catch (IOException e) {
			displayError("ERROR!! REGISTRY FILE IS NOT SAVED.");
		}
	}

	@Override
	public void logIn(String username, String password) {
		authentication.logIn(username, password);
		if (authentication.isLoggedIn())
			displaySuccess("LOG IN SUCCESSFULL");
		else
			displayError("LOG IN FAILED");
	}

	@Override
	public ISearchStrategy doSimpleSearch() {
		SearchStrategy strategy = new SearchStrategy();
		ISearchStrategy simpleSearchStrategy = null;

		System.out.println("\nSELECT THE OPTION");

		for (SimpleSearchMode mode : SimpleSearchMode.values())
			System.out.println(mode.ordinal() + ": " + mode);

		System.out.println(returnSequence + ": RETURN");
		System.out.print(quitSequence + ": SAVE & QUIT\n>");
		input = scan.next();

		if (input.equals(returnSequence))
			displayMainInstructions();
		else if (input.equals(quitSequence))
			quitProgram();
		else {
			try {
				switch (SimpleSearchMode.values()[Integer.parseInt(input)]) {

				case BY_NAME:
					simpleSearchStrategy = strategy.getSearchByName(getMemberNameFromUser());
					break;
				case BY_MONTH:
					simpleSearchStrategy = strategy.getSearchByMonth(getSearchMonthFromUser());
					break;
				case BY_BOAT_TYPE:
					simpleSearchStrategy = strategy.getSearchByBoatType(getBoatTypeFromUser());
					break;
				case BY_AGE_EQUAL_TO:
					simpleSearchStrategy = strategy.getSearchByAgeEqualTo(getSearchAgeFromUser());
					break;
				case BY_AGE_GREATER_THAN:
					simpleSearchStrategy = strategy.getSearchByAgeGreaterThan(getSearchAgeFromUser());
					break;
				case BY_AGE_LESS_THAN:
					simpleSearchStrategy = strategy.getSearchByAgeLessThan(getSearchAgeFromUser());
					break;
				case BY_BOAT_LENGTH:
					simpleSearchStrategy = strategy.getSearchByBoatLength(getBoatLengthFromUser());
					break;
				default:
					break;
				}
			} catch (Exception e) {
				displayError("INVALID OPTION");
				doSimpleSearch();
			}
		}
		return simpleSearchStrategy;
	}

	@Override
	public void doComplexSearch() {
		/*
		SearchStrategy strategy = new SearchStrategy();
		ISearchStrategy complexSearchStrategy = doSimpleSearch();
		
		String in;

		do {
			for (ComplexSearchMode operator : ComplexSearchMode.values())
				System.out.println(operator.ordinal() + ": " + operator);

			System.out.println("s: SHOW RESULT");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": SAVE & QUIT\n");
			in = scan.next();

			if (in.equals(returnSequence))
				return;
			else if (in.equals(quitSequence))
				quitProgram();
			else if (in.equals("s"))
				displaySearchResult(registry.search(complexSearchStrategy));
			else {
				try {
					switch (ComplexSearchMode.values()[Integer.parseInt(in)]) {

					case AND:
						complexSearchStrategy = strategy.getByAndStrategy(complexSearchStrategy, doSimpleSearch());
						break;
					case OR:
						complexSearchStrategy = strategy.getByOrStrategy(complexSearchStrategy, doSimpleSearch());
						break;
					}
				} catch (Exception e) {
					displayError("INVALID OPTION");
				}
			}
		} while (!in.equals("s"));
		*/
		model.Search.ByComplex complexSearch = new model.Search.ByComplex(); 
		String in;

		complexSearch.add(doSimpleSearch(), model.Search.SearchStrategy.ComplexSearchMode.OR);
		
		do {
			for (ComplexSearchMode operator : ComplexSearchMode.values())
				System.out.println(operator.ordinal() + ": " + operator);

			System.out.println("s: SHOW RESULT");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": SAVE & QUIT\n");
			in = scan.next();

			if (in.equals(returnSequence))
				return;
			else if (in.equals(quitSequence))
				quitProgram();
			else if (in.equals("s"))
				displaySearchResult(registry.search(complexSearch));
			else {
				try {
					complexSearch.add(doSimpleSearch(), ComplexSearchMode.values()[Integer.parseInt(in)]);
				} catch (Exception e) {
					displayError("INVALID OPTION");
				}
			}
		} while (!in.equals("s"));

	}

	@Override
	public void displaySearchResult(ArrayList<Member> m) {
		if (!m.isEmpty()) {
			displaySuccess(m.size() + " RESULT(S) FOUND");
			displayVerboseList(m);
		} else
			displayError("NO RESULT FOUND");
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
			if (!authentication.isLoggedIn())
				System.out.println("4: LOG IN");
			System.out.println(returnSequence + ": RETURN");
			System.out.print(quitSequence + ": SAVE & QUIT\n>");

			Member member;
			input = scan.next();
			if (!authentication.isLoggedIn() && !input.equals(returnSequence) && !input.equals(quitSequence)
					&& !input.equals("4")) {
				displayError("ACCESS DENIED!! PLEASE LOG IN");
				continue;
			}

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
			case ("4"):
				if (!authentication.isLoggedIn())
					logIn(getUsernameFromUser(), getPasswordFromUser());
				else
					displayError("INVALID OPTION");
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
		while (input != quitSequence || input != returnSequence) {
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

	private Double getBoatLengthFromUser() {
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

	private String getUsernameFromUser() {
		System.out.print("USERNAME?\n>");
		input = scan.next();
		return input;
	}

	private String getPasswordFromUser() {
		System.out.print("PASSWORD?\n>");
		input = scan.next();
		return input;
	}

	private int getSearchAgeFromUser() {
		System.out.print("AGE\n>");
		input = scan.next();
		while (!checkSerachAge(input)) {
			displayError("INCORRECT AGE !! PLEASE TYPE AGAIN");
			input = scan.next();
		}
		return Integer.parseInt(input);
	}

	private int getSearchMonthFromUser() {
		System.out.println("+ ID | MONTH     +");
		int i = 0;
		for (Month m : Month.values())
			System.out.printf("%5d|%11s|\n", ++i, m.toString());
		System.out.print("ENTER MONTH ID\n>");
		input = scan.next();
		while (!checkMonth(input)) {
			displayError("INVALID MONTH ID");
			input = scan.next();
		}
		return Integer.parseInt(input);
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

	private boolean checkIndex(String input, int size) {
		try {
			return Integer.parseInt(input) > 0 && Integer.parseInt(input) <= size;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean checkBoatLength(String input) {
		try {
			Double.parseDouble(input);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean checkSerachAge(String age) {
		try {
			return (Integer.parseInt(age) >= 0 && Integer.parseInt(age) <= 140); // max age
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean checkMonth(String month) {
		try {
			return Month.of(Integer.parseInt(month)) != null;
		} catch (Exception e) {
			return false;
		}
	}
}
