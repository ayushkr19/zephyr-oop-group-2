package exceptions;

public class EventListNOtPublishedException extends Exception {
	@Override
	public String getMessage() {
		return "EventList Not Published Yet";
	}
}
