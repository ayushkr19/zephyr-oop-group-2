package departments;

public class Dosm extends Department{

	public long budget() {
		return 400;
	}

	public  void getStalls(){
		System.out.println("Get stalls by dosm");
	}
	
	public  void getSponsors(){
		System.out.println("Getting sponsors by dosm");
	}
	
	public boolean takePayment(){
		System.out.println("Take payments from stalls & give to zephyr controls");
		return true;
	}
}
