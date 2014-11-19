package controls;

import java.util.ArrayList;

import participatingbody.participants.HostelRep;
import participatingbody.participants.Judge;
import participatingbody.participants.Participant;

public class EventsHead extends ZephyrControls {

	public void manageEvents(){
		System.out.println("Events head managing event");
	}
	
	public void notifyParticipantsAndJudges(ArrayList<Participant> participants, Judge judge, ArrayList<HostelRep> hostelReps){
		for (HostelRep hostelRep : hostelReps){
			hostelRep.notifyParticipants();
		}
	}
	
	public void publishRulebook(){
		System.out.println("Publishing rulebook");
	}
	
	public void publishEventList(){
		System.out.println("Publishing events list");
	}
}
