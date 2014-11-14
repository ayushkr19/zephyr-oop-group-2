package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
import events.eventutils.ScoreBoard;
import exceptions.BudgetNotApprovedException;
import exceptions.HostelNotFoundException;
import exceptions.PermissionsDeniedException;
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

	public static void main(String[] args) throws PermissionsDeniedException, BudgetNotApprovedException, HostelNotFoundException {
		System.out.println("Starting Up");

		// Initializing variables
		Random random = new Random();
		
		initialize();

		System.out.println("Number of Students : " + allStudents.size() + " ,Number of Hostels : "
				+ allHostels.size() + ", Number of Events : "
				+ allCompetitiveEvents.size());

		// Pre -fest stuff

		csa.acquirePermissions();
		if (!administration.grantPermissions(true)) {
			throw new PermissionsDeniedException();
		}

		PlannedBudget plannedBudget = financeHead.planBudget();;

		convener.manageDepartments();
		convener.finalizePerformers();

		for(Performer performer : allPerformers){
			plannedBudget.getQuotes(performer);
		}
	
		for (Department dept : allDepartments) {
			plannedBudget.getQuotes(dept);
		}
		csa.coordinateBudget();
		csa.finalizeVendor();

		dosm.getSponsors();
		dosm.getStalls();
		financeHead.coordinateSponsorship();

		plannedBudget.decideRegistrationFee();
		plannedBudget.decideSP();
		
		if(!administration.approveBudget(plannedBudget)){
			throw new BudgetNotApprovedException();
		}
		
		FinalizedBudget finalizedBudget = financeHead
				.finalizeBudget(plannedBudget);

		chiefCoordinator.assignVerticals();
		chiefCoordinator.promote();
		controlsMember.carryPRDrives();
		controlsMember.putUpPosters();

		artsNDeco.decorate();

		eventsHead.publishEventList();
		eventsHead.publishRulebook();
		eventsHead.manageEvents();
		
		
		// During fest stuff
		for (CompetitiveEvents competitiveEvents : allCompetitiveEvents) {
			controlsMember.procureMaterials();
			controlsMember.manageEvent();
			convener.bookRooms();
			dopy.coverEvent();
			
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
			
			judge.judgeEvent();
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
		for( Hostel hostel: allHostels){
			System.out.println("Hostel : " + hostel.getName() + ", Position : " + hostel.getPosition()); 
		}
		ScoreBoard.displayWinner();
		dopy.releasePics();
		financeHead.reviewBudgets();
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
			ScoreBoard.getScoresMap().put(allHostels.get(i), 0L);
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
				event = new CompetitiveEvents(EventType.GOLD);
				break;
			case 1:
				event = new CompetitiveEvents(EventType.SILVER);
				break;
			case 2:
				event = new CompetitiveEvents(EventType.BRONZE);
				break;
			default:
				event = new CompetitiveEvents(EventType.GOLD);
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
	}

	private static void initializeStudents() {
		allStudents = new ArrayList<Student>();

		for (int i = 1; i <= Constants.NUMBER_OF_STUDENTS; i++) {
			Student student;
			switch (i % 13) {
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
			default:
				student = new Student("Student" + i, "AH3", "bits" + i, true);
				break;

			}

			allStudents.add(student);
		}
	}

}
