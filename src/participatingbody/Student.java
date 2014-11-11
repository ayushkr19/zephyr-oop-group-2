package participatingbody;

public class Student {

	private String name;
	private String hostel;
	private String ID;
	
	/*
	 * If student is attending nights or not
	 */
	private boolean attends;
	
	

	public Student(String name, String hostel, String iD, boolean attends) {
		super();
		this.name = name;
		this.hostel = hostel;
		ID = iD;
		this.attends = attends;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostel() {
		return hostel;
	}

	public void setHostel(String hostel) {
		this.hostel = hostel;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public boolean isAttends() {
		return attends;
	}

	public void setAttends(boolean attends) {
		this.attends = attends;
	}
	
	public void attendNights(){
		
	}
	
	public void orderTshirts(){
		
	}
	
	public void purchaseFromStalls(){
		
	}
	
	
	
}
