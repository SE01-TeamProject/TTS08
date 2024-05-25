package ui;

import java.util.*;

public class SwingController {
	private LoginWindow loginWindow = new LoginWindow(this);
	private MainWindow mainWindow;// = new MainWindow(this);;
	private boolean projectSelectFlag = false;
	
	// SwingController(Model model){}
	
	public SwingController() {
		loginWindow.setVisible(true);
	
	}
	
	public void addUser(String id, String pw, String name, String level) {
		System.out.println("Add User - [ID: " + id + "] [PW: " + pw + "] [Name: " + name + "] [Level: " + level + "]");
	}
	
	public void addProject(String title, String description) {
		System.out.println("[Title: " + title + "] [Description: " + description + "]");
	}
	
	public boolean getProjectFlag() {
		return projectSelectFlag;
	}
	
	public void projectSelect() {
		setProjectFlag(true);
	}
	
	public void setProjectFlag(boolean flag) {
		projectSelectFlag = flag;
	}
	
	// Log in & out --------------------------------------------	
	public boolean login(String id, String password) {
		/* Ask Model to Login -> if True, on the Main, off the Login
		 * */
		System.out.println("SC - ID: " + id + ",  PASSWORD: " + password);
		
		if(true) {
			// Login Success. Every login, initialize mainWindow
			mainWindow = new MainWindow(this);
			mainWindow.setUser(id);
			
			loginWindow.setVisible(false);
			mainWindow.setVisible(true);
			return true;
		}		
		return false;
	}
		
	public void logout() {
		/* Ask Model to Logout -> if True, off the Main, on the Login
		 * */
		System.out.println("SC - Logout");
		mainWindow.setUser("UNKNOWN");
		
		setProjectFlag(false);	
		loginWindow.setVisible(true);
		mainWindow.setVisible(false);
		
	}
	
	// Getters -------------------------------------------------
	public ArrayList<String> getPriorties(){
		ArrayList<String> array = new ArrayList<String>();		
		ArrayList<String> priority = new ArrayList<String>(Arrays.asList("Blocker", "Critical", "Major", "Minor", "Trivial"));	// Change this -> Use model call
		
		for(String pri : priority) {
			array.add(pri);
		}		
		return array;
	}
	
	public ArrayList<String> getMilestones(){
		ArrayList<String> array = new ArrayList<String>();		
		ArrayList<String> milestones = new ArrayList<String>(Arrays.asList("Milestone1", "Mileston2", "Mileston3", "Milestone4"));	// Change this -> Use model call
		
		for(String stone : milestones) {
			array.add(stone);
		}
		return array;
	}
	
	public ArrayList<String> getStates(){
		ArrayList<String> array = new ArrayList<String>();		
		ArrayList<String> milestones = new ArrayList<String>(Arrays.asList("New", "Assigned", "Resolved", "Closed", "Reopened"));	// Change this -> Use model call
		
		for(String stone : milestones) {
			array.add(stone);
		}
		return array;
	}
	
	public ArrayList<String> getLevelString(){
		/* Return levels into String list -> Used in adding new users
		 * */
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> levels = new ArrayList<String>(Arrays.asList("tester", "dev", "project leader"));		// Change this -> Use model call
		
		for(String level : levels) {
			array.add(level);
		}
		return array;
	}
	
	public ArrayList<String> getDummyTicket() {
		ArrayList<String> ticket = new ArrayList<String>();
		
		return ticket;
	}
	
	public ArrayList<String> getTestUsers(){
		// Return user list, EVERY USER
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> users = new ArrayList<String>(Arrays.asList("tester1", "tester2", "PL1", "PL2"));		// Change this -> Use model call
		
		for(String user : users) {
			array.add(user);
		}
		return array;
	}
	
	public ArrayList<String> getTestUsersIncluded(){
		// Return user list, users included in current project
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> users = new ArrayList<String>(Arrays.asList("tester1", "tester2", "PL1", "PL2"));		// Change this -> Use model call
		
		for(String user : users) {
			array.add(user);
		}
		return array;
	}
}
