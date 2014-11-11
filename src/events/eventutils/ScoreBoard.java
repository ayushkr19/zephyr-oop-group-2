package events.eventutils;

import java.util.HashMap;
import java.util.Map;

import exceptions.HostelNotFoundException;

import participatingbody.Hostel;

public class ScoreBoard {

	private static HashMap<Hostel, Long> scoresMap;
	
	
	public ScoreBoard() {
		// TODO Auto-generated constructor stub
		scoresMap = new HashMap<Hostel, Long>();
	}

	public static HashMap<Hostel, Long> getScoresMap() {
		return scoresMap;
	}

	public static void setScoresMap(HashMap<Hostel, Long> scoresMap) {
		ScoreBoard.scoresMap = scoresMap;
	}

	public static Long getScores(Hostel hostel) throws HostelNotFoundException{
		if(scoresMap.containsKey(hostel)){
			return scoresMap.get(hostel);
		}else{
			//Throw non existent hostel exception
			throw new HostelNotFoundException();
			
		}
	}
	
	public static void updateScores(Hostel hostel, Long score, UpdateScoreType updateScoreType){
		//Update scores for a particular hostel
		if(scoresMap.containsKey(hostel)){
			
			if(updateScoreType == UpdateScoreType.ADD){
				score = scoresMap.get(hostel) + score;
			}else if(updateScoreType == UpdateScoreType.SUBTRACT){
				score = scoresMap.get(hostel) - score;
			}
			
			scoresMap.put(hostel,score);
		}else{
			//Throw non existent hostel exception
		}
	}
	
	public static void displayScores(){
		//Get all hostels & display scores
		if(scoresMap != null){
			for(Map.Entry<Hostel, Long> entry : scoresMap.entrySet()){
				System.out.println("Hostel : " + entry.getKey() + ", Score : " + entry.getValue());
			}
		}else{
			//Throw scoresMap is null exception
		}
		
	}
}
