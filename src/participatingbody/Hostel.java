package participatingbody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import events.CompetitiveEvents;
import events.eventutils.ScoreBoard;
import exceptions.HostelNotFoundException;

public class Hostel {

	private ArrayList<CompetitiveEvents> bettedEvents;
	private ArrayList<CompetitiveEvents> participatedEvents;
	
	
	public Hostel(ArrayList<CompetitiveEvents> bettedEvents,
			ArrayList<CompetitiveEvents> participatedEvents) {
		super();
		this.bettedEvents = bettedEvents;
		this.participatedEvents = participatedEvents;
	}
	
	

	public Hostel() {
		super();
		bettedEvents = new ArrayList<CompetitiveEvents>();
		participatedEvents = new ArrayList<CompetitiveEvents>();
	}



	public ArrayList<CompetitiveEvents> getParticipatedEvents() {
		return participatedEvents;
	}

	public void setParticipatedEvents(
			ArrayList<CompetitiveEvents> participatedEvents) {
		this.participatedEvents = participatedEvents;
	}

	public ArrayList<CompetitiveEvents> getBettedEvents() {
		return bettedEvents;
	}

	public void setBettedEvents(ArrayList<CompetitiveEvents> bettedEvents) {
		this.bettedEvents = bettedEvents;
	}
	
	public int getNumOfEventsParticipatedIn(){
		return participatedEvents.size();
	}

	public Long getScores() throws HostelNotFoundException{
		if(ScoreBoard.getScoresMap().containsKey(this)){
			return ScoreBoard.getScores(this);
		}else{
			//Throw hostel not present in scores
		}
		return 0L;
	}
	
	public int getPosition() throws HostelNotFoundException{
		HashMap<Hostel, Long> scoresMap = ScoreBoard.getScoresMap();
		
		Long thisHostelScore = getScores();
		int position = 1;
		
		if(scoresMap != null){
			for(Map.Entry<Hostel, Long> entry : scoresMap.entrySet()){
				//System.out.println("Hostel : " + entry.getKey() + ", Score : " + entry.getValue());
				
				if(entry.getKey() != this){
					if(entry.getValue() > thisHostelScore){
						position++;
					}
				}
				
			}	
		}
		
		return position;
	}
	
	public void addBettedEvent(CompetitiveEvents competitiveEvents){
		if(!bettedEvents.contains(competitiveEvents)){
			bettedEvents.add(competitiveEvents);
		}
		
	}

	
	
	
}
