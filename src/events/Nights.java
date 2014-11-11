package events;

import java.util.ArrayList;

import participatingbody.Performer;
import participatingbody.Student;

public class Nights {

	private ArrayList<Student> attendees;
	
	public ArrayList<Student> getAttendees() {
		return attendees;
	}
	
	public void setAttendees(ArrayList<Student> attendees) {
		this.attendees = attendees;
	}
	
	public Nights(ArrayList<Student> attendees) {
		this.attendees = attendees;
	}

	public Nights() {
		attendees = new ArrayList<Student>();
	}

	public void registerStudent(Student student){
		if(!attendees.contains(student)){
			attendees.add(student);
		}
	}
	
	public boolean isRegistered(Student student){
		if(attendees.isEmpty()){
			return false;
		}else if(attendees.contains(student)){
			return true;
		}else{
			return false;	
		}
	}
	
	public void displayRegisteredStudents(){
		if(attendees.isEmpty()){
			System.out.println("No registered students!");
			return;
		}
		for(Student student : attendees){
			System.out.println("Registered : " + student.getName());
		}
	}
	
	public void perform(Performer performer){
		performer.perform();
	}
}
