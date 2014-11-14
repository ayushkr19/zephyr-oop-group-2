package main;

import java.util.ArrayList;

import participatingbody.Administration;
import participatingbody.Hostel;
import participatingbody.Performer;
import participatingbody.Student;
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
import exceptions.PermissionsDeniedException;
import finance.FinalizedBudget;
import finance.PlannedBudget;

public class Main {

	static ArrayList<Student> allStudents;
	static ArrayList<Hostel> allHostels;
	static ArrayList<CompetitiveEvents> allCompetitiveEvents;
	static ArrayList<Nights> allNights;
	static ArrayList<Department> allDepartments;
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
	static Performer performer1;
	static Performer performer2;
	static Performer performer3;
	

	public static void main(String[] args) throws PermissionsDeniedException {
		System.out.println("Starting Up");
		
		// Initializing variables
		initializeHostels();
		initializeStudents();
		initializeEvents();
		initializeNights();
		initializeDepartments();
		initializeAdministration();
		initializeControls();
		initializePerformers();

		System.out.println("Stu : " + allStudents.size() + " Hos : "
				+ allHostels.size() + " Events : "
				+ allCompetitiveEvents.size());
		
		// Pre -fest stuff
		
		csa.acquirePermissions();
		if(!administration.grantPermissions()){
			throw new PermissionsDeniedException();
		}
		
		chiefCoordinator.assignVerticals();
		
		chiefCoordinator.promote();
		controlsMember.carryPRDrives();
		controlsMember.putUpPosters();
		
		
		PlannedBudget plannedBudget = financeHead.planBudget();;
		
		convener.manageDepartments();
		convener.finalizePerformers();
		
		plannedBudget.getQuotes(performer1);
		plannedBudget.getQuotes(performer2);
		plannedBudget.getQuotes(performer3);
		for(Department dept : allDepartments){
			plannedBudget.getQuotes(dept);
		}
		csa.coordinateBudget();
		csa.finalizeVendor();
		
		dosm.getSponsors();
		financeHead.coordinateSponsorship();
		
		plannedBudget.decideRegistrationFee();
		plannedBudget.decideSP();
		
		dosm.getStalls();
		
		artsNDeco.decorate();
		
		FinalizedBudget finalizedBudget = financeHead.finalizeBudget(plannedBudget);
		
		eventsHead.publishEventList();
		eventsHead.publishRulebook();
		eventsHead.manageEvents();
		// During fest stuff
		for (CompetitiveEvents competitiveEvents : allCompetitiveEvents){
			controlsMember.procureMaterials();
			controlsMember.manageEvent();
			eventsHead.notifyParticipantsAndJudges();
			
			dopy.coverEvent();
			
		}
		
		
		
		// Post fest stuff
		dopy.releasePics();
	}

	private static void initializePerformers() {
		performer1 = new Performer();
		performer2 = new Performer();
		performer3 = new Performer();
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
		Hostel AH1 = new Hostel();
		Hostel AH2 = new Hostel();
		Hostel AH3 = new Hostel();
		Hostel AH4 = new Hostel();
		Hostel AH5 = new Hostel();
		Hostel AH6 = new Hostel();
		Hostel AH7 = new Hostel();
		Hostel AH8 = new Hostel();
		Hostel CH1 = new Hostel();
		Hostel CH2 = new Hostel();
		Hostel CH3 = new Hostel();
		Hostel CH4 = new Hostel();
		Hostel CH5 = new Hostel();
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
