package exceptions;

public class PermissionsDeniedException extends Exception {
	@Override
	public String getMessage() {
		return "Permission denied by administration to continue with Zephyr";
	}
}
