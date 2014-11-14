package test;

import static org.junit.Assert.*;

import org.junit.Test;

import events.eventutils.Inventory;
import events.eventutils.Inventory.InventoryItem;
import exceptions.NotEnoughItemsException;

public class InventoryTest {

	Inventory inv = new Inventory();
	@Test
	public void testItemsAddedOrSubtracted() throws NotEnoughItemsException {
		inv.deposit(InventoryItem.BAG, 2);
		inv.deposit(InventoryItem.BALL, 3);
		
		inv.withdraw(InventoryItem.BAG, 2);
		inv.withdraw(InventoryItem.BALL, 3);
		assertEquals(Inventory.getQuantity()[InventoryItem.BAG.ordinal()],0);
		assertEquals(Inventory.getQuantity()[InventoryItem.BALL.ordinal()],0);
		
	}
	
	@Test(expected=NotEnoughItemsException.class)
	public void withdrawTooMuchTest() throws NotEnoughItemsException{
		inv.withdraw(InventoryItem.BAG, 1);
	}

}
