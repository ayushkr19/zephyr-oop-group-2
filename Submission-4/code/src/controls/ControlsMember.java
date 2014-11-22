package controls;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import events.CompetitiveEvents;
import events.eventutils.Constants;
import events.eventutils.Inventory;
import exceptions.NotEnoughItemsException;

import java.util.HashMap;
import java.util.Map;
public class ControlsMember extends ZephyrControls {
	
	private String[] fixeditems = {"BAG", "BALL", "HANKIE", "ROPE", "SHEET"};

	public void procureMaterials(ArrayList<CompetitiveEvents> allCompetitiveEvents){
		Inventory.set("BAG", 100);
		Inventory.set("BALL", 100);
		Inventory.set("HANKIE", 100);
		Inventory.set("ROPE", 100);
		Inventory.set("SHEET", 100);
		System.out.println("Procured materials succesfully!");
	}
	
	public void withdrawRequiredItems(CompetitiveEvents competitiveEvents) throws NotEnoughItemsException{
		int[] quantityRequired = competitiveEvents.getQuantity();
		
		
		
		for(int i=0; i<Constants.MAX_ITEMS_IN_INVENTORY; i++){
			if(quantityRequired[i] > 0){
				
				System.out.println("Withdrawing " + quantityRequired[i] + fixeditems[i] +" from inventory for " + competitiveEvents.getName());
				Inventory.withdraw(fixeditems[i], quantityRequired[i]);
			}
		}
	}
	
	public double carryPRDrives(){
		System.out.println("Carrying PR drives");
		Random random = new Random();
		double rating = random.nextInt(5)+5;
		return rating;
	}
	
	public double putUpPosters(){
		System.out.println("Putting up posters");
		Random random = new Random();
		double rating = random.nextInt(5)+5;
		return rating;
	}
	
	public void manageEvent(CompetitiveEvents competitiveEvents){
		System.out.println("Managing event" + competitiveEvents.getName());
	}
}
