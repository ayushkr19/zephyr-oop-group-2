package departments;

import events.CompetitiveEvents;

public class Dopy extends Department{

	public long budget() {
		return 300;
	}
	
	public void coverEvent(CompetitiveEvents competitiveEvents){
		System.out.println("Covering event " + competitiveEvents.getName() + " by dopy");
	}
	
	public void releasePics(){
		System.out.println("Releasing pics by dopy");
	}

}
