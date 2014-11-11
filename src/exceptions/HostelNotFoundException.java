package exceptions;

public class HostelNotFoundException extends Exception{
	@Override
	public String getMessage() {
		return "Hostel not found";
	}
}
