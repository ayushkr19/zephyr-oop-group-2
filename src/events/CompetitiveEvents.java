package events;

import java.util.ArrayList;

import events.eventutils.Constants;
import events.eventutils.EventType;
import events.eventutils.ScoreBoard;
import events.eventutils.UpdateScoreType;
import participatingbody.Hostel;
import participatingbody.Student;
import participatingbody.participants.Participant;

public class CompetitiveEvents {
	
	private EventType eventType;
	private ArrayList<Participant> participants;
	
	public CompetitiveEvents(EventType eventType,
			ArrayList<Participant> participants) {
		this.eventType = eventType;
		this.participants = participants;
	}

	public CompetitiveEvents(EventType eventType) {
		this();
		this.eventType = eventType;
	}

	public CompetitiveEvents() {
		participants = new ArrayList<Participant>();
	}



	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public boolean isEventBettedForHostel(Hostel hostel){
		ArrayList<CompetitiveEvents> bettedEvents = hostel.getBettedEvents();
		if(bettedEvents != null){
			return bettedEvents.contains(this);
		}
		//TODO: Check for null
		return false;
	}
	
	public void register(Student student){
		Participant participant = new Participant(student);
		if(!participants.contains(participant)){
			participants.add(participant);
		}
		
	}
	
	public void declareResults(Hostel hostel){
		Long bettedScore = (isEventBettedForHostel(hostel) ? Constants.BETTED_EVENT_SCORE : 0L);
		switch(eventType){
		case GOLD:
			ScoreBoard.updateScores(hostel, Constants.GOLD_EVENT_SCORE + bettedScore, UpdateScoreType.ADD);
			break;
		case SILVER:
			ScoreBoard.updateScores(hostel, Constants.SILVER_EVENT_SCORE + bettedScore, UpdateScoreType.ADD);
			break;
		case BRONZE:
			ScoreBoard.updateScores(hostel, Constants.BRONZE_EVENT_SCORE + bettedScore, UpdateScoreType.ADD);
			break;
		}
	}
	
	public void distributePrizes(){
		
	}
	
	public void notifyParticipants(){
		
	}
}
