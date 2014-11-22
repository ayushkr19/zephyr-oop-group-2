package exceptions;

public class RulebookNOtPublishedException extends Exception {
	@Override
	public String getMessage() {
		return "RuleBook Not Published Yet";
	}
}



