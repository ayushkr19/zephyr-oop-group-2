package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import departments.*;
import exceptions.NotEnoughItemsException;

public class BudgetTest {
Dopy bud=new Dopy();
public void testBudget(){
	assertFalse(bud.budget(),0L);
}

}
