package exceptions;

public class RoomsNotBookedException extends Exception {
	@Override
	public String getMessage() {
		return "Rooms not booked!";
	}
}
