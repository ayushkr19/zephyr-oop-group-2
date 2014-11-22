package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import participatingbody.Administration;
import participatingbody.Hostel;
import participatingbody.Performer;
import participatingbody.Student;
import participatingbody.participants.HostelRep;
import participatingbody.participants.Judge;
import participatingbody.participants.Participant;
import controls.CSA;
import controls.ChiefCoordinator;
import controls.ControlsMember;
import controls.Convener;
import controls.EventsHead;
import controls.FinanceHead;
import departments.ArtsNDeco;
import departments.Backstage;
import departments.Department;
import departments.Dopy;
import departments.Dosm;
import events.CompetitiveEvents;
import events.Nights;
import events.eventutils.Constants;
import events.eventutils.EventType;
import events.eventutils.Inventory;
import events.eventutils.ScoreBoard;
import exceptions.BudgetNotApprovedException;
import exceptions.EventListNOtPublishedException;
import exceptions.HostelNotFoundException;
import exceptions.NotEnoughItemsException;
import exceptions.PermissionsDeniedException;
import exceptions.RoomsNotBookedException;
import exceptions.RulebookNOtPublishedException;
import finance.FinalizedBudget;
import finance.PlannedBudget;
import finance.Stalls;
import finance.TshirtVendor;

public class Main {

	static ArrayList<Student> allStudents;
	static ArrayList<Hostel> allHostels;
	static ArrayList<CompetitiveEvents> allCompetitiveEvents;
	static ArrayList<Nights> allNights;
	static ArrayList<Department> allDepartments;
	static ArrayList<HostelRep> allHostelReps;
	static ArrayList<Performer> allPerformers;
	static Dosm dosm;
	static ArtsNDeco artsNDeco;
	static Backstage backstage;
	static Dopy dopy;
	static Administration administration;
	static ChiefCoordinator chiefCoordinator;
	static ControlsMember controlsMember;
	static Convener convener;
	static CSA csa;
	static EventsHead eventsHead;
	static FinanceHead financeHead;
	static ScoreBoard scoreBoard;
	static Stalls stalls;
	static TshirtVendor tshirtVendor;
	static Inventory inventory;
	static Performer performer;
	
	

	public static void main(String[] args) throws PermissionsDeniedException, BudgetNotApprovedException, HostelNotFoundException, NotEnoughItemsException, EventListNOtPublishedException, RulebookNOtPublishedException, RoomsNotBookedException {
		System.out.println("Starting Up");

		// Initializing variables
		Random random = new Random();
		
		try{
			 //final String JDBC_DRIVER = "org.h2.Driver";  
			    final String DB_URL = "jdbc:h2:~/test";
			    Statement stmt;
			    Connection conn;

			   //  Database credentials
			    final String USER = "sa";
			    final String PASS = "";
			Class.forName("org.h2.Driver");
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
			stmt.execute("DROP TABLE SCOREMAP;");
			stmt.execute("DROP TABLE INVENTORY;");
			stmt.execute("CREATE TABLE SCOREMAP (Hostel VARCHAR PRIMARY KEY , Score LONG);");
			stmt.execute("CREATE TABLE Inventory (Item VARCHAR, Quantity INT);");
			try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
			}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		initialize();
		
		//Inventory.truncate();
		//ScoreBoard.truncate();

		System.out.println("Number of Students : " + allStudents.size() + " ,Number of Hostels : "
				+ allHostels.size() + ", Number of Events : "
				+ allCompetitiveEvents.size());

		// Pre -fest stuff
		
		/**
		 * Acquire permission
		 */
		csa.acquirePermissions();
		System.out.println("Grant permission for events? (1 for yes/ 2 for no) ");
		Scanner in = new Scanner(System.in);
		int con = in.nextInt();
		boolean fincon = false;
		if(con==1){
			fincon=true;
		}
		if (!administration.grantPermissions(fincon)) {
			throw new PermissionsDeniedException();
		}

		/**
		 * Plan budget by finance head
		 */
		PlannedBudget plannedBudget = financeHead.planBudget();

		
		/**
		 * Finalize performers & manage departments by convener
		 */
		convener.manageDepartments();
		convener.finalizePerformers();
		
		/**
		 * Coordinate sponsorship with Dosm
		 */
		financeHead.coordinateSponsorship();
		
		/**
		 * Calculate planned budget by getting quotes
		 */
		long allPerformerQuotes = 0;
		long estimatedStudentsAttending = 0;
		for(Performer performer : allPerformers){
			allPerformerQuotes += plannedBudget.getQuotes(performer);
			estimatedStudentsAttending += (performer.estimatedStudentReach()/allPerformers.size());
		}
	
		for (Department dept : allDepartments) {
			plannedBudget.getQuotes(dept);
		}
		
		/**
		 * Coordinate & finalize budget 
		 */
		csa.coordinateBudget();
		csa.finalizeVendor();
			

		/**
		 * Decide Registration fees & SP of tees
		 */
		plannedBudget.decideRegistrationFee(allPerformerQuotes,estimatedStudentsAttending);
		plannedBudget.decideSP();
		
		if(!administration.approveBudget(plannedBudget)){
			throw new BudgetNotApprovedException();
		}
		financeHead.reviewBudgets();
		FinalizedBudget finalizedBudget = financeHead
				.finalizeBudget(plannedBudget);

		chiefCoordinator.assignVerticals();
		double r1 = chiefCoordinator.promote();
		double r2 = controlsMember.carryPRDrives();
		double r3 = controlsMember.putUpPosters();

		double r4 = artsNDeco.decorate();
		double r7 = performer.perform();

		eventsHead.publishEventList();
		System.out.println("Has EventList been Published?  (1 for yes/ 2 for no) ");
		Scanner in1 = new Scanner(System.in);
		int con1 = in1.nextInt();
		boolean fincon1 = false;
		if(con1==1){
			fincon1 = true;
		}
		if (fincon1==false) {
			throw new EventListNOtPublishedException();
		}	
		eventsHead.publishRulebook();
		System.out.println("Has Rulebook been Published?  (1 for yes/ 2 for no) ");
		Scanner in2 = new Scanner(System.in);
		int con2 = in2.nextInt();
		boolean fincon2 = false;
		if(con2==1){
			fincon2 = true;
		}
		if (fincon2==false) {
			throw new RulebookNOtPublishedException();
		}	
		eventsHead.manageEvents();
		
		controlsMember.procureMaterials(allCompetitiveEvents);
		dosm.takePayment();
		convener.bookRooms();
		System.out.println("Has the rooms been booked?  (1 for yes/ 2 for no) ");
		Scanner in3 = new Scanner(System.in);
		int con3 = in3.nextInt();
		boolean fincon3 = false;
		if(con3==1){
			fincon3 = true;
		}
		if (fincon3==false) {
			
				throw new RoomsNotBookedException();
			
		}
		// During fest stuff
		for (CompetitiveEvents competitiveEvents : allCompetitiveEvents) {
			
			System.out.println("______________" + competitiveEvents.getName() + "______________");
			System.out.println(" ");
			System.out.println(" ");
			controlsMember.withdrawRequiredItems(competitiveEvents);
			controlsMember.manageEvent(competitiveEvents);
			
			dopy.coverEvent(competitiveEvents);
			
			int lowerBound = Constants.MIN_NUMBER_OF_PARTICIPANTS_FOR_EVENT;
			int pseudoUpperBound = Constants.MAX_NUMBER_OF_PARTICIPANTS_FOR_EVENT - lowerBound;
			int NumberOfParticipants = lowerBound + random.nextInt(pseudoUpperBound);
			
			Collections.shuffle(allStudents);
			for(int i=0; i<NumberOfParticipants; i++){
				competitiveEvents.register(allStudents.get(i));
			}
			
			Judge judge = new Judge(allStudents.get(NumberOfParticipants));
			eventsHead.notifyParticipantsAndJudges(competitiveEvents.getParticipants(),judge, allHostelReps);
			
			for(Participant participant : competitiveEvents.getParticipants()){
				participant.participateInEvent();
			}
			
			judge.judgeEvent(competitiveEvents);
			Participant winner =  competitiveEvents.getParticipants().get(random.nextInt(NumberOfParticipants));
			String winningHostel = judge.declareResults(competitiveEvents,winner);
			for(Hostel hostel: allHostels){
				if (hostel.getName().equalsIgnoreCase(winningHostel)){
					competitiveEvents.declareResults(hostel);
				}
			}
			judge.distributePrizes(winner);
			
		}
		
		Collections.shuffle(allStudents);
		int numberOfStudentsBuying = Constants.MIN_NUMBER_OF_STUDENTS_PURCHASING + random.nextInt(Constants.MAX_NUMBER_OF_STUDENTS_PURCHASING - Constants.MIN_NUMBER_OF_STUDENTS_PURCHASING);
		for(int i=0; i<numberOfStudentsBuying ; i++){
			stalls.sellItems(allStudents.get(i));
		}
		
		tshirtVendor.getGoodies();
		tshirtVendor.takePayments();
		tshirtVendor.supplyTshirts(allHostelReps);
		
		int PerformerIndex = 0;
		for(Nights nights : allNights){
			int lowerBound = 800;
			int pseudoUpperBound = 1300;
			int NumberOfStudentsForNights = lowerBound + random.nextInt(pseudoUpperBound);
			
			Collections.shuffle(allStudents);
			for(int i=0; i<NumberOfStudentsForNights; i++){
				nights.registerStudent(allStudents.get(i));
			}
			
			//nights.displayRegisteredStudents();
			nights.displayNumberOfRegisteredStudents(PerformerIndex);
			nights.perform(allPerformers.get(PerformerIndex));
			PerformerIndex++;
		}

		// Post fest stuff
		ScoreBoard.displayScores();
		/*for( Hostel hostel: allHostels){
			System.out.println("Hostel : " + hostel.getName() + ", Position : " + hostel.getPosition()); 
		}*/
		ScoreBoard.displayPositions();
		ScoreBoard.displayWinner();
		double r5 = dopy.releasePics();
		double r6 = (r1+r2+r3+r4+r5+r7)/6;
		System.out.println("The rating for Zephyr is " + r6 + "/10");
		
		
		
	
	}

	private static void initialize() {
		initializeHostels();
		initializeStudents();
		initializeHostelReps();
		initializeEvents();
		initializeNights();
		initializeDepartments();
		initializeAdministration();
		initializeControls();
		initializePerformers();
		initializeStalls();
		initializeVendors();
		initializeScoreBoard();
	}


	private static void initializeVendors() {
		tshirtVendor = new TshirtVendor();
	}

	private static void initializeStalls() {
		stalls = new Stalls();
	}

	private static void initializeHostelReps() {
		allHostelReps = new ArrayList<HostelRep>();
		for(int i=0; i<allHostels.size(); i++){
			Student rep = null;
			for(Student student: allStudents){
				if(allHostels.get(i).getName().equalsIgnoreCase(student.getHostel())){
					rep = student;
					break;
				}
			}
			HostelRep hostelRep = new HostelRep(rep);
			allHostelReps.add(hostelRep);
		}
	}

	private static void initializeScoreBoard() {
		scoreBoard = new ScoreBoard();
		for(int i=0; i<allHostels.size(); i++){
			ScoreBoard.setScoresMap(allHostels.get(i), 0L);
		}
	}

	private static void initializePerformers() {
		allPerformers = new ArrayList<Performer>();
		
		for(int i=0; i<allNights.size(); i++){
			Performer performer = new Performer();
			allPerformers.add(performer);
		}
		
	}

	private static void initializeControls() {
		chiefCoordinator = new ChiefCoordinator();
		controlsMember = new ControlsMember();
		convener = new Convener();
		csa = new CSA();
		eventsHead = new EventsHead();
		financeHead = new FinanceHead();
		dosm = new Dosm();
		performer = new Performer();
	}

	private static void initializeAdministration() {
		administration = new Administration();
	}

	private static void initializeDepartments() {
		allDepartments = new ArrayList<Department>();

		dosm = new Dosm();
		artsNDeco = new ArtsNDeco();
		backstage = new Backstage();
		dopy = new Dopy();

		allDepartments.add(dosm);
		allDepartments.add(dopy);
		allDepartments.add(artsNDeco);
		allDepartments.add(backstage);

	}

	private static void initializeNights() {
		allNights = new ArrayList<Nights>();
		Nights night1 = new Nights();
		Nights night2 = new Nights();
		Nights night3 = new Nights();

		allNights.add(night1);
		allNights.add(night2);
		allNights.add(night3);

	}

	private static void initializeEvents() {
		allCompetitiveEvents = new ArrayList<CompetitiveEvents>();

		for (int i = 1; i <= Constants.NUMBER_OF_COMPETITIVE_EVENTS; i++) {
			CompetitiveEvents event;
			switch (i % 3) {
			case 0:
				event = new CompetitiveEvents(EventType.GOLD, "Event" + i);
				break;
			case 1:
				event = new CompetitiveEvents(EventType.SILVER, "Event" + i);
				break;
			case 2:
				event = new CompetitiveEvents(EventType.BRONZE, "Event" + i);
				break;
			default:
				event = new CompetitiveEvents(EventType.GOLD, "Event" + i);
				break;
			}

			allCompetitiveEvents.add(event);
		}
	}

	private static void initializeHostels() {
		allHostels = new ArrayList<Hostel>();
		Hostel AH1 = new Hostel("AH1");
		Hostel AH2 = new Hostel("AH2");
		Hostel AH3 = new Hostel("AH3");
		Hostel AH4 = new Hostel("AH4");
		Hostel AH5 = new Hostel("AH5");
		Hostel AH6 = new Hostel("AH6");
		Hostel AH7 = new Hostel("AH7");
		Hostel AH8 = new Hostel("AH8");
		Hostel CH1 = new Hostel("CH1");
		Hostel CH2 = new Hostel("CH2");
		Hostel CH3 = new Hostel("CH3");
		Hostel CH4 = new Hostel("CH4");
		Hostel CH5 = new Hostel("CH5");
		Hostel CH6 = new Hostel("CH6");
		
		allHostels.add(AH1);
		allHostels.add(AH2);
		allHostels.add(AH3);
		allHostels.add(AH4);
		allHostels.add(AH5);
		allHostels.add(AH6);
		allHostels.add(AH7);
		allHostels.add(AH8);
		allHostels.add(CH1);
		allHostels.add(CH2);
		allHostels.add(CH3);
		allHostels.add(CH4);
		allHostels.add(CH5);
		allHostels.add(CH6);
	}

	private static void initializeStudents() {
		allStudents = new ArrayList<Student>();

		for (int i = 1; i <= Constants.NUMBER_OF_STUDENTS; i++) {
			Student student;
			switch (i % 14) {
			case 0:
				student = new Student("Student" + i, "AH1", "bits" + i, true);
				break;
			case 1:
				student = new Student("Student" + i, "AH2", "bits" + i, true);
				break;
			case 2:
				student = new Student("Student" + i, "AH3", "bits" + i, true);
				break;
			case 3:
				student = new Student("Student" + i, "AH4", "bits" + i, true);
				break;
			case 4:
				student = new Student("Student" + i, "AH5", "bits" + i, true);
				break;
			case 5:
				student = new Student("Student" + i, "AH6", "bits" + i, true);
				break;
			case 6:
				student = new Student("Student" + i, "AH7", "bits" + i, true);
				break;
			case 7:
				student = new Student("Student" + i, "AH8", "bits" + i, true);
				break;
			case 8:
				student = new Student("Student" + i, "CH1", "bits" + i, true);
				break;
			case 9:
				student = new Student("Student" + i, "CH2", "bits" + i, true);
				break;
			case 10:
				student = new Student("Student" + i, "CH3", "bits" + i, true);
				break;
			case 11:
				student = new Student("Student" + i, "CH4", "bits" + i, true);
				break;
			case 12:
				student = new Student("Student" + i, "CH5", "bits" + i, true);
				break;
			case 13:
				student = new Student("Student" + i, "CH6", "bits" + i, true);
				break;
			default:
				student = new Student("Student" + i, "AH3", "bits" + i, true);
				break;

			}

			allStudents.add(student);
		}
	}

}
