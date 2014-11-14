package events.eventutils;

public class Constants {

	/**
	 * Event Scores depending on type. If a new Event type has to be added,
	 * it has to be added to the {@link EventType} Enum too.
	 */
	public static final Long GOLD_EVENT_SCORE = 500L;
	public static final Long SILVER_EVENT_SCORE = 300L;
	public static final Long BRONZE_EVENT_SCORE = 200L;
	public static final Long DISQUALIFIED_EVENT_SCORE = 0L;
	
	/**
	 * Score that has to be added if an event has been betted
	 */
	public static final Long BETTED_EVENT_SCORE = 200L;
	
	/**
	 * Hold the max number of items of a particular type.
	 */
	public static final int MAX_ITEMS_IN_INVENTORY = 10;
	
	/**
	 * Registration fee for the nights
	 */
	public static final long NIGHTS_REGISTRATION_FEE = 500;
	
	/**
	 * Profit from each Tshirt
	 */
	public static final long PROFIT_PER_TEE = 50;
	
	/**
	 * Number of students
	 */
	public static final int NUMBER_OF_STUDENTS = 180 * 13;
	
	/**
	 * Number of competitive events
	 */
	public static final int NUMBER_OF_COMPETITIVE_EVENTS = 15;
	
	/**
	 * Cost Price of TShirts
	 */
	public static final long TSHIRT_CP = 200;
	/**
	 * Max budget that can be allowed by the administration 
	 */
	public static final long MAX_ALLOWABLE_BUDGET = 13000;
}
