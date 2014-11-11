package events.eventutils;

import exceptions.NotEnoughItemsException;

public class Inventory {

	/**
	 * Enum containing the types of objects we want to store in the inventory
	 */
	public static enum InventoryItem {BALL, BAG, HANKIE, SHEET, ROPE }

	/**
	 * Array to hold the quantity of each Item
	 */
	public static int[] quantity = new int[Constants.MAX_ITEMS_IN_INVENTORY]; 

	/**
	 * Getter for quantity
	 * @return quantity
	 */
	public static int[] getQuantity() {
		return quantity;
	}

	/**
	 * Setter for quantity
	 * @param quantity
	 */
	public static void setQuantity(int[] quantity) {
		Inventory.quantity = quantity;
	}
	/**
	 * Deposit a type of item in the inventory
	 * @param type of item
	 * @param quantity
	 */
	public void deposit(InventoryItem type, int q){
		quantity[type.ordinal()] += q;
	}
	
	/**
	 * Withdraw a particular type of item from the inventory
	 * @param type of item
	 * @param quantity
	 * @throws NotEnoughItemsException
	 */
	public void withdraw(InventoryItem type, int q) throws NotEnoughItemsException{
		int qt = quantity[type.ordinal()];
		if (qt-q < 0)
		{
			throw new NotEnoughItemsException("Not enough Items");
		}
		quantity[type.ordinal()] -= q;
	}
	
	/**
	 * Display all items in inventory
	 */
	public void displayItems(){
		for (InventoryItem i : InventoryItem.values())
		{
			System.out.println(i.name() + " : " + quantity[i.ordinal()]);
		}
	}
}
