package events.eventutils;

import exceptions.NotEnoughItemsException;

public class Inventory {

	
	public static enum InventoryItem {BALL, BAG, HANKIE, SHEET, ROPE };
	static int[] quantity = new int[10]; 

	
	public static int[] getQuantity() {
		return quantity;
	}

	public static void setQuantity(int[] quantity) {
		Inventory.quantity = quantity;
	}

	public void deposit(InventoryItem type, int q){
		quantity[type.ordinal()] += q;
	}
	
	public void withdraw(InventoryItem type, int q) throws NotEnoughItemsException{
		int qt = quantity[type.ordinal()];
		if (qt-q < 0)
		{
			throw new NotEnoughItemsException("");
		}
		quantity[type.ordinal()] -= q;
	}
	
	public void displayItems(){
		for (InventoryItem i : InventoryItem.values())
		{
			System.out.println(i.name() + " : " + quantity[i.ordinal()]);
		}
	}
}
