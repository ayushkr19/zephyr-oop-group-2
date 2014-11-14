package controls;

import finance.FinalizedBudget;
import finance.PlannedBudget;

public class FinanceHead extends ZephyrControls {

	public void coordinateSponsorship(){
		
	}
	
	public PlannedBudget planBudget(){
		return new PlannedBudget();
	}
	
	public FinalizedBudget finalizeBudget(PlannedBudget plannedbudget){
		return plannedbudget.finalizeBudget();
	}
	
	public void makePayments(){
		
	}
	
	public void collectRequirements(){
		
	}
	
	public void reviewBudgets(){
		
	}
}
