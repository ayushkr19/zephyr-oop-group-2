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
	
}
