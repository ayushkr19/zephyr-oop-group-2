package participatingbody;

import events.eventutils.Constants;
import finance.PlannedBudget;

public class Administration {

	public boolean grantPermissions(boolean b) {
		return b;
	}

	public void grantReimbursements() {

	}

	public boolean approveBudget(PlannedBudget plannedBudget) {
		if (plannedBudget.getBudget() < Constants.MAX_ALLOWABLE_BUDGET)
			return true;
		else
			return false;
	}
}
