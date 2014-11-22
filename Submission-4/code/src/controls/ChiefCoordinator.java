package controls;

import java.util.Random;

import events.eventutils.Constants;

/**
 * Chief coordinator
 *
 */
public class ChiefCoordinator extends ZephyrControls {

	public double promote(){
		System.out.println("Promoting event");
		Random random = new Random();
		double rating = random.nextInt(5)+5;
		return rating;
		
		
	}
	
	public void assignVerticals(){
		System.out.println("Assigning verticals");
	}
	
	
}
