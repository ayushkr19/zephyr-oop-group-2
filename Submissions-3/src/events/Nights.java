package events;

import java.util.ArrayList;

import participatingbody.Performer;
import participatingbody.Student;

public class Nights {
	
	/**
	 * List of students attending the night
	 */
	private ArrayList<Student> attendees;
	
	private static long registrationFee;

	/**
	 * Constructor
	 * @param attendees
	 */
	public Nights(ArrayList<Student> attendees) {
		this.attendees = attendees;
	}

	/**
	 * Constructor
	 */
	public Nights() {
		attendees = new ArrayList<Student>();
	}
	
	
	
	/**
	 * Getter for the list of attendees
	 * @return attendees
	 */
	public ArrayList<Student> getAttendees() {
		return attendees;
	}
	
	/**
	 * Setter for the attendees list
	 * @param attendees
	 */
	public void setAttendees(ArrayList<Student> attendees) {
		this.attendees = attendees;
	}
	

	/**
	 * @return the registrationFee
	 */
	public long getRegistrationFee() {
		return registrationFee;
	}

	/**
	 * @param registrationFee the registrationFee to set
	 */
	public static void setRegistrationFee(long registrationFee) {
		Nights.registrationFee = registrationFee;
	}

	/**
	 * Register a student for the night
	 * @param student
	 */
	public void registerStudent(Student student){
		if(!attendees.contains(student)){
			attendees.add(student);
		}
	}
	
	/**
	 * Find out if a student is registered for the night or not
	 * @param student
	 * @return true if registered, else false
	 */
	public boolean isRegistered(Student student){
		if(attendees.isEmpty()){
			return false;
		}else if(attendees.contains(student)){
			return true;
		}else{
			return false;	
		}
	}
	
	/**
	 * Display all students registered for the night
	 */
	public void displayRegisteredStudents(){
		if(attendees.isEmpty()){
			System.out.println("No registered students!");
			return;
		}
		for(Student student : attendees){
			System.out.println( student.getName() + " with ID:" + student.getID() + " has registered for Nights");
		}
	}
	
	/**
	 * Let performer perform for the night
	 * @param performer
	 */
	public void perform(Performer performer){
		performer.perform();
	}

	public void displayNumberOfRegisteredStudents(int performerIndex) {
		System.out.println("Number of students registered for Nights " + (performerIndex+1) + " : " + attendees.size());
	}
}
