package participatingbody;

public class Student {

	/*
	 * Name of the student
	 */
	private String name;
	/*
	 * Hostel the student belongs to
	 */
	private String hostel;
	/*
	 * ID of the student
	 */
	private String ID;
	
	/*
	 * If student is attending nights or not
	 */
	private boolean attends;
	
	
	/**
	 * Constructor
	 * @param name
	 * @param hostel
	 * @param iD
	 * @param attends nights or not
	 */
	public Student(String name, String hostel, String iD, boolean attends) {
		super();
		this.name = name;
		this.hostel = hostel;
		ID = iD;
		this.attends = attends;
	}

	/**
	 * Getter for Name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for hostel
	 * @return hostel
	 */
	public String getHostel() {
		return hostel;
	}

	/**
	 * Setter for hostel
	 * @param hostel
	 */
	public void setHostel(String hostel) {
		this.hostel = hostel;
	}

	/**
	 * Getter for ID
	 * @return ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Setter for ID
	 * @param iD
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * If student attends nights or not
	 * @return true if attends, else false
	 */
	public boolean isAttends() {
		return attends;
	}
	/**
	 * Setter for attends
	 * @param attends
	 */
	public void setAttends(boolean attends) {
		this.attends = attends;
	}
	
	/**
	 * Attend Nights
	 */
	public void attendNights(){
		
	}
	
	/**
	 * Order Tshirts
	 */
	public void orderTshirts(){
		
	}
	
	/**
	 * Purchase from stalls
	 */
	public void purchaseFromStalls(){
		
	}
	
	
	
}
