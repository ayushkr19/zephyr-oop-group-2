package controls;

public class Convener extends ZephyrControls {

	public boolean acquirePermissions(){
		System.out.println("Acquiring permissions");
		return true;
	}
	
	public boolean bookRooms(){
		System.out.println("Booking rooms");
		return true;
	}
	
	public boolean finalizePerformers(){
		System.out.println("Finalizing performers");
		return true;
	}
	
	public boolean manageDepartments(){
		System.out.println("Managing departments");
		return true;
	}
	
	
}
