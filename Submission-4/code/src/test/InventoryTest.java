package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import events.eventutils.Inventory;
import exceptions.NotEnoughItemsException;

public class InventoryTest {

	Inventory inv = new Inventory();
	@Test
	public void testItemsAddedOrSubtracted() throws NotEnoughItemsException {
		int before = Inventory.getQuantity("BAG");
		Inventory.deposit("BAG", 2);
		Inventory.withdraw("BAG", 2);
		assertEquals(Inventory.getQuantity("BAG"),before);
		
	}
	
	@Test(expected=exceptions.NotEnoughItemsException.class)
	public void withdrawTooMuchTest() throws NotEnoughItemsException{
		inv.withdraw("BAG", 105);
	}

}
