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
		if(scoresMap != null){
			for(Map.Entry<Hostel, Long> entry : scoresMap.entrySet()){
				System.out.println("Hostel : " + entry.getKey().getName() + ", Score : " + entry.getValue());
			}
		}else{
			System.out.println("No scores exist for any hostel");
		}
		
	}
	
	/**
	 * Display winner of Zephyr
	 */
	public static void displayWinner(){
		if(scoresMap != null){
			
			HashMap<Hostel, Long> winners = new HashMap<Hostel,Long>();
			
			Long highestScore = Long.MIN_VALUE;
			
			for(Map.Entry<Hostel, Long> entry : scoresMap.entrySet()){
				if(entry.getValue() > highestScore){
					winners.clear();
					winners.put(entry.getKey(), entry.getValue());
					highestScore = entry.getValue();
				}else if( entry.getValue().equals(highestScore)){
					winners.put(entry.getKey(), entry.getValue());
				}
			}
			
			if(winners.size() == 0){
				System.out.println("No winner of Zephyr!");
			}else if(winners.size() == 1){
				for(Map.Entry<Hostel, Long> entry : winners.entrySet()){
					System.out.println("Hostel " + entry.getKey().getName() + " is the winner of Zephyr with " + entry.getValue() + " points! Congratulations!" );
				}
			}else  if(winners.size() > 1){
				String winningHostels = "";
				Long winningScore = 0L;
				for(Map.Entry<Hostel, Long> entry : winners.entrySet()){
					if(winningHostels.equals("")){
						winningHostels = entry.getKey().getName();
					}else{
						winningHostels = winningHostels + ", " +entry.getKey().getName();
					}
					
					winningScore = entry.getValue();
				}
				System.out.println("Hostels " + winningHostels + " are the joint winners of Zephyr with " + winningScore + " points! Congratulations!");
			}
			
		}else{
			System.out.println("No scores exist for any hostel");
		}
	}
	
}
