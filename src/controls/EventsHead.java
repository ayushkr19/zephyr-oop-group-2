package controls;

import java.util.ArrayList;

import participatingbody.participants.HostelRep;
import participatingbody.participants.Judge;
import participatingbody.participants.Participant;

public class EventsHead extends ZephyrControls {

	public void manageEvents(){
		
	}
	
	public void notifyParticipantsAndJudges(ArrayList<Participant> participants, Judge judge, ArrayList<HostelRep> hostelReps){
		for (HostelRep hostelRep : hostelReps){
			hostelRep.notifyParticipants();
		}
	}
	
	public void publishRulebook(){
		
	}
	
	public void publishEventList(){
		
	}
}
