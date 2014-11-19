package controls;

import finance.FinalizedBudget;
import finance.PlannedBudget;

public class FinanceHead extends ZephyrControls {

	public void coordinateSponsorship(){
		System.out.println("Coordinate sponsorship with dosm");
	}
	
	public PlannedBudget planBudget(){
		return new PlannedBudget();
	}
	
	public FinalizedBudget finalizeBudget(PlannedBudget plannedbudget){
		return plannedbudget.finalizeBudget();
	}
	
	public void makePayments(){
		System.out.println("Making payments");
	}
	
	public void collectRequirements(){
		System.out.println("Collect requirements");
	}
	
	public void reviewBudgets(){
		
	}
}
