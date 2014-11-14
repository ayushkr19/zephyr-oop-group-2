package participatingbody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import events.CompetitiveEvents;
import events.eventutils.ScoreBoard;
import exceptions.HostelNotFoundException;

public class Hostel {

	/**
	 * Name
	 */
	private String name;
	/**
	 * List of betted events for this hostel
	 */
	private ArrayList<CompetitiveEvents> bettedEvents;
	/**
	 * List of events this hostel has participated in
	 */
	private ArrayList<CompetitiveEvents> participatedEvents;
	
	/**
	 * Constructor
	 * @param name
	 * @param bettedEvents
	 * @param participatedEvents
	 */
	public Hostel(String name,ArrayList<CompetitiveEvents> bettedEvents,
			ArrayList<CompetitiveEvents> participatedEvents) {
		super();
		this.setName(name);
		this.bettedEvents = bettedEvents;
		this.participatedEvents = participatedEvents;
	}
	
	
	/**
	 * Constructor
	 */
	public Hostel(String name) {
		super();
		this.setName(name);
		bettedEvents = new ArrayList<CompetitiveEvents>();
		participatedEvents = new ArrayList<CompetitiveEvents>();
	}


	/**
	 * Getter for participated Events
	 * @return
	 */
	public ArrayList<CompetitiveEvents> getParticipatedEvents() {
		return participatedEvents;
	}

	/**
	 * Setter for participated Events
	 * @param participatedEvents
	 */
	public void setParticipatedEvents(
			ArrayList<CompetitiveEvents> participatedEvents) {
		this.participatedEvents = participatedEvents;
	}

	/**
	 * Getter for Betted Events
	 * @return
	 */
	public ArrayList<CompetitiveEvents> getBettedEvents() {
		return bettedEvents;
	}

	/**
	 * Setter for Betted Events
	 * @param bettedEvents
	 */
	public void setBettedEvents(ArrayList<CompetitiveEvents> bettedEvents) {
		this.bettedEvents = bettedEvents;
	}
	
	/**
	 * Getter for number of events participated in
	 * @return size
	 */
	public int getNumOfEventsParticipatedIn(){
		return participatedEvents.size();
	}

	/**
	 * Get scores for this hostel
	 * @return score
	 * @throws HostelNotFoundException
	 */
	public Long getScores() throws HostelNotFoundException{
		if(ScoreBoard.getScoresMap().containsKey(this)){
			return ScoreBoard.getScores(this);
		}else{
			//Throw hostel not present in scores
		}
		return 0L;
	}
	
	/**
	 * Get position among all hostels
	 * @return position
	 * @throws HostelNotFoundException
	 */
	public int getPosition() throws HostelNotFoundException{
		HashMap<Hostel, Long> scoresMap = ScoreBoard.getScoresMap();
		
		Long thisHostelScore = getScores();
		int position = 1;
		
		if(scoresMap != null){
			for(Map.Entry<Hostel, Long> entry : scoresMap.entrySet()){
				
				if(entry.getKey() != this){
					if(entry.getValue() > thisHostelScore){
						position++;
					}
				}
				
			}	
		}
		
		return position;
	}
	
	/**
	 * Add event to betted list
	 * @param competitiveEvents
	 */
	public void addBettedEvent(CompetitiveEvents competitiveEvents){
		if(!bettedEvents.contains(competitiveEvents)){
			bettedEvents.add(competitiveEvents);
		}
		
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
