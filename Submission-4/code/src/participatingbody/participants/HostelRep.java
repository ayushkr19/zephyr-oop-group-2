package participatingbody.participants;

import participatingbody.Student;

public class HostelRep extends Student{

	public HostelRep(Student student){
		super(student.getName(), student.getHostel(), student.getID(),student.isAttends());
	}
	
	public HostelRep(String name, String hostel, String iD, boolean attends) {
		super(name, hostel, iD, attends);
	}

	public void submitBettedEvent(){
		
	}
	
	public void distributeTees(){
		
	}
	
	public void notifyParticipants(){
		
	}
}
