package participatingbody.participants;

import participatingbody.Student;

public class Participant extends Student{
	
	public Participant(Student student) {
		super(student.getName(), student.getHostel(), student.getID(),student.isAttends());
	}

	public void participateInEvent(){
		
	}
	
	public void collectPrize(){
		
	}
}
