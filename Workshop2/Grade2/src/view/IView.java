package view;

import java.util.ArrayList;
import model.Boat;
import model.Boat.BoatType;
import model.Member;

/**
 * Collection of methods both in GUI and Console application
 *
 */
public interface IView {
	
	/**
	 * Starts program. Welcome message and main instructions follows.
	 */
	void startProgram();
	
	/**
	 * 	The method displays welcome message.
	 */
	void displayWelcomeMessage();
	
	/**
	 * This method should provide main options of the program.
	 */
	void displayMainInstructions();
	
	/**
	 * The method displays given given member list in compact list form.
	 * It includes member id, name, number of boats.
	 * @param m
	 */
	void displayCompactList(ArrayList<Member> m);
	
	/**
	 * The method displays given given member list in verbose form.
	 * It includes all information of a member
	 * @param m
	 */
	void displayVerboseList(ArrayList<Member> m);
	
	/**
	 * Register a new member on registry based on provided parameters
	 * @param name
	 * @param personalnumber
	 */
	void registerMember(String name, String personalnumber);
	
	/**
	 * Updates provided member according to provided parameters
	 * @param m
	 * @param name
	 * @param personalnumber
	 */
	void updateMember(Member m, String name, String personalnumber);
	
	/**
	 * Delete provided member
	 * @param m
	 */
	void deleteMember(Member m);
	
	/**
	 * Display all information which given member has.
	 * This prints in same format as verbose provides.
	 * @param m
	 */
	void displaySelectedMember(Member m);
	
	/**
	 * Register a new boat to a given member in registry based on provided parameters
	 * @param m
	 * @param boatLength
	 * @param boattype
	 */
	void registerBoat(Member m, double boatLength, BoatType boattype);

	/**
	 * Updates provided boat according to provided parameters
	 * @param length
	 * @param type
	 * @param boat
	 */
	void updateBoat(Member m, double length, BoatType type, Boat boat);
	
	/**
	 * Delete provided boat from provided member's boat list
	 * @param m
	 * @param b
	 */
	void deleteBoat(Member m, Boat b);

	void displayError(String error);

	void displaySuccess(String success);
	
	/**
	 * It terminates program. It saves current registry to files.
	 */
	void quitProgram();
}