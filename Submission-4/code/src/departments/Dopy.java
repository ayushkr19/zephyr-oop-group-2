package departments;

import java.util.Random;

import events.CompetitiveEvents;

public class Dopy extends Department{


	Random random = new Random();
	double rating = random.nextInt(5)+5;
		
	public long budget() {
		long bud = (long) rating;
		return bud*300;
	}
	
	public void coverEvent(CompetitiveEvents competitiveEvents){
		System.out.println("Covering event " + competitiveEvents.getName() + " by dopy");
	}
	
	public double releasePics(){
		System.out.println("Releasing pics by dopy");
		return rating;
	}

}
