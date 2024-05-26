package com.example.demo.ui;

import java.util.*;
import org.json.*;
import org.json.JSONObject;
import org.json.simple.*;
import com.example.demo.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;


public class SwingController {
	private LoginWindow loginWindow = new LoginWindow(this);
	private MainWindow mainWindow;// = new MainWindow(this);;
	private boolean projectSelectFlag = false;
	private String urlString = "http://localhost:8080";
	
	//private URL url;
	//private HttpURLConnection connection;
	
	// SwingController(Model model){}
	
	public SwingController() throws IOException {
		loginWindow.setVisible(true);
		
		
		try {
			//url = new URL("http://localhost:8080/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//connection = (HttpURLConnection) url.openConnection();
		
	
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
	public boolean login(String id, String password) throws UnsupportedEncodingException, IOException {
		/* Ask Model to Login -> if True, on the Main, off the Login
		 * */
		//System.out.println("SC - ID: " + id + ",  PASSWORD: " + password);
		
		try {
			JSONObject loginInfo = new JSONObject();
			loginInfo.put("id", id);
			loginInfo.put("password", password);
			
			URL url = new URL(urlString+"/login");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
	
			connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
			
			System.out.println(loginInfo.toString());
			
			// JSON 데이터를 바이트 스트림으로 변환하여 출력 스트림에 작성
			try(OutputStream os = connection.getOutputStream();
	                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")) {
	                osw.write(loginInfo.toString());
	                osw.flush(); // 데이터 전송
	        }

			 
			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			
			try(BufferedReader br = new BufferedReader(
	                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
	                StringBuilder response = new StringBuilder();
	                String responseLine = null;
	                while ((responseLine = br.readLine()) != null) {
	                    response.append(responseLine.trim());
	                }
	                System.out.println("HTTP Response Body:" + response.toString());
	                
	                if(response.toString().equals("true")) {
	    				// Login Success. Every login, initialize mainWindow
	    				mainWindow = new MainWindow(this);
	    				mainWindow.setUser(id);
	    				
	    				loginWindow.setVisible(false);
	    				mainWindow.setVisible(true);
	    				return true;
	    			}
	                
	    			return false;
	        }
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
