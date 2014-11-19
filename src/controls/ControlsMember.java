package controls;

import java.util.ArrayList;

import events.CompetitiveEvents;
import events.eventutils.Constants;
import events.eventutils.Inventory;
import events.eventutils.Inventory.InventoryItem;
import exceptions.NotEnoughItemsException;

public class ControlsMember extends ZephyrControls {

	public void procureMaterials(ArrayList<CompetitiveEvents> allCompetitiveEvents){
		Inventory.deposit(InventoryItem.BAG, 25);
		Inventory.deposit(InventoryItem.BALL, 45);
		Inventory.deposit(InventoryItem.HANKIE, 30);
		Inventory.deposit(InventoryItem.ROPE, 50);
		Inventory.deposit(InventoryItem.SHEET, 20);
		System.out.println("Procured materials succesfully!");
	}
	
	public void withdrawRequiredItems(CompetitiveEvents competitiveEvents) throws NotEnoughItemsException{
		int[] quantityRequired = competitiveEvents.getQuantity();
		
		for(int i=0; i<Constants.MAX_ITEMS_IN_INVENTORY; i++){
			if(quantityRequired[i] > 0){
				
				System.out.println("Withdrawing " + quantityRequired[i] + " items from inventory for " + competitiveEvents.getName());
				Inventory.withdraw(InventoryItem.values()[i], quantityRequired[i]);
			}
		}
	}
	
	public void carryPRDrives(){
		System.out.println("Carrying PR drives");
	}
	
	public void putUpPosters(){
		System.out.println("Putting up posters");
	}
	
	public void manageEvent(CompetitiveEvents competitiveEvents){
		System.out.println("Managing event" + competitiveEvents.getName());
	}
}
