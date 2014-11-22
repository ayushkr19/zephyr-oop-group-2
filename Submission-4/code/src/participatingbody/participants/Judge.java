package participatingbody.participants;

import events.CompetitiveEvents;
import participatingbody.Student;

public class Judge extends Student{

	public Judge(Student student){
		super(student.getName(), student.getHostel(), student.getID(),student.isAttends());
	}
	
	public Judge(String name, String hostel, String iD, boolean attends) {
		super(name, hostel, iD, attends);
	}

	public void judgeEvent(CompetitiveEvents competitiveEvents){
		System.out.println("Judging event " + competitiveEvents.getName());
	}
	
	public String declareResults(CompetitiveEvents competitiveEvents, Participant participant){
		String hostelString = participant.getHostel();
		return hostelString;
	}
	
	public void distributePrizes(Participant winner){
		System.out.println("Distributing prize to " + winner.getName() + " of hostel " + winner.getHostel());
		winner.collectPrize();
	}
}
