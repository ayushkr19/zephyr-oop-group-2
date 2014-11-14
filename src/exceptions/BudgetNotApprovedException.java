package exceptions;

public class BudgetNotApprovedException extends Exception {
	@Override
	public String toString() {
		return "Budget not approved by administration";
	}
}
