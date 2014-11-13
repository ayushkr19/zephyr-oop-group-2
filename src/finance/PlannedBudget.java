package finance;

import events.Nights;
import events.eventutils.Constants;
import participatingbody.Performer;

public class PlannedBudget {

	private long budget;
	
	public PlannedBudget() {
		budget = 0;
	}

	public PlannedBudget(long budget) {
		this.budget = budget;
	}

	public void getQuotes(Performer performer){
		long performerQuotes = performer.getQuotes();
		budget = budget + performerQuotes;
	}
	
	public void decideSP(){
		TshirtVendor.setSP(TshirtVendor.getCP() + Constants.PROFIT_PER_TEE);
	}
	
	public void decideRegistrationFee(){
		Nights.setRegistrationFee(Constants.NIGHTS_REGISTRATION_FEE);
	}
	
	public FinalizedBudget finalizeBudget(){
		return new FinalizedBudget(budget);
	}
	
	public void updateBudget(){
		
	}
}
