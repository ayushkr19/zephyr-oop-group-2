package events.eventutils;

import java.util.HashMap;
import java.util.Map;

import exceptions.HostelNotFoundException;

import participatingbody.Hostel;

public class ScoreBoard {

	/**
	 * Constructor
	 */
	public ScoreBoard() {
		scoresMap = new HashMap<Hostel, Long>();
	}
	
	/**
	 * HashMap to contain scores of hostels
	 */
	private static HashMap<Hostel, Long> scoresMap;
	
	/**
	 * Getter for scores map
	 * @return
	 */
	public static HashMap<Hostel, Long> getScoresMap() {
		return scoresMap;
	}

	/**
	 * Setter for scores map
	 * @param scoresMap
	 */
	public static void setScoresMap(HashMap<Hostel, Long> scoresMap) {
		ScoreBoard.scoresMap = scoresMap;
	}

	/**
	 * Get scores of a particular hostel
	 * @param hostel
	 * @return score
	 * @throws HostelNotFoundException
	 */
	public static Long getScores(Hostel hostel) throws HostelNotFoundException{
		if(scoresMap.containsKey(hostel)){
			return scoresMap.get(hostel);
		}else{
			//Throw non existent hostel exception
			throw new HostelNotFoundException();
			
		}
	}
	
	/**
	 * Update scores of a hostel
	 * @param hostel
	 * @param score
	 * @param updateScoreType
	 * @throws HostelNotFoundException 
	 */
	public static void updateScores(Hostel hostel, Long score, UpdateScoreType updateScoreType) throws HostelNotFoundException{
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
			throw new HostelNotFoundException();
		}
	}
	
	/**
	 * Display scores of all Hostels
	 */
	public static void displayScores(){
		//Get all hostels & display scores
		if(scoresMap != null){
			for(Map.Entry<Hostel, Long> entry : scoresMap.entrySet()){
				System.out.println("Hostel : " + entry.getKey() + ", Score : " + entry.getValue());
			}
		}else{
			System.out.println("No scores exist for any hostel");
		}
		
	}
}
