package events;

import java.util.ArrayList;

import events.eventutils.Constants;
import events.eventutils.EventType;
import events.eventutils.ScoreBoard;
import events.eventutils.UpdateScoreType;
import exceptions.HostelNotFoundException;
import participatingbody.Hostel;
import participatingbody.Student;
import participatingbody.participants.Participant;

public class CompetitiveEvents {
	/**
	 * Enum to hold type of event : GOLD,SILVER etc
	 */
	private EventType eventType;
	
	/**
	 * List of participants for this event
	 */
	private ArrayList<Participant> participants;
	
	/**
	 * Constructor
	 * @param eventType
	 * @param participants
	 */
	public CompetitiveEvents(EventType eventType,
			ArrayList<Participant> participants) {
		this.eventType = eventType;
		this.participants = participants;
	}

	/**
	 * Constructor
	 * @param eventType
	 */
	public CompetitiveEvents(EventType eventType) {
		this();
		this.eventType = eventType;
	}

	/**
	 * Constructor
	 */
	public CompetitiveEvents() {
		participants = new ArrayList<Participant>();
	}

	/**
	 * Getter of EventType
	 * @return eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * Setter of EventType
	 * @param eventType
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * If an event is betted, then additional score has to be
	 * added to the event's score.
	 * @param hostel
	 * @return true if event is betted for the hostel
	 */
	public boolean isEventBettedForHostel(Hostel hostel){
		ArrayList<CompetitiveEvents> bettedEvents = hostel.getBettedEvents();
		if(bettedEvents != null){
			return bettedEvents.contains(this);
		}
		//TODO: Check for null
		return false;
	}
	
	/**
	 * Register a student for an event
	 * @param student
	 */
	public void register(Student student){
		Participant participant = new Participant(student);
		if(!participants.contains(participant)){
			participants.add(participant);
		}
		
	}
	
	/**
	 * Declare results of an event
	 * @param hostel
	 * @throws HostelNotFoundException 
	 */
	public void declareResults(Hostel hostel) throws HostelNotFoundException{
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
	
	/**
	 * Distribute Prizes
	 */
	public void distributePrizes(){
		
	}
	
	/**
	 * Notify participants
	 */
	public void notifyParticipants(){
		
	}
}
