package participatingbody;

import java.util.Random;

import events.eventutils.Constants;

public class Performer {
	
	private int popularity_level = 0;
	
	public Performer() {
		Random random = new Random();
		popularity_level = Constants.LOWEST_POPULARITY_LEVEL + random.nextInt(10 - Constants.LOWEST_POPULARITY_LEVEL);
	}

	public double perform(){
		Random random = new Random();
		double rating = random.nextInt(3)+7;
		return rating;
	}
	
	public long estimatedStudentReach(){
		return 200 * popularity_level;
	}
	
	public long getQuotes(){
		return 5000 * popularity_level;
	}
}
