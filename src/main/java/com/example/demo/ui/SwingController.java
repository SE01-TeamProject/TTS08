package com.example.demo.ui;

import java.util.*;
import org.json.*;
import org.json.JSONArray;
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
	private String userID;
	private String currProject;
	
	//private URL url;
	//private HttpURLConnection connection;
	
	// SwingController(Model model){}
	
	public SwingController() throws IOException {
	
		try {
			loginWindow.setVisible(true);
			//url = new URL("http://localhost:8080/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("GOOD: " + requestGET("/issueTitle/Issue1"));
		
		//connection = (HttpURLConnection) url.openConnection();
	}
	
	// Issue (Ticket) ------------------------------------------------------------------------------
	//GET
	public String[] getIssueHeader() {
		String[] header = {"id", "title", "description", "date"};
		return header;
	}
	
	public String[][] getIssueContent() {
		String[][] content;
		
		String response = requestGET("/listIssue");
		
		String[] keys = getIssueHeader();
        content = extractValues(response, keys);
        System.out.println(content);
        return content;
	}
	
	
	
	// Users ---------------------------------------------------------------------------------
	public String getCurrUser() {
		return userID;
	}
	
	public void addUser(String id, String pw, String name, int level) {
		System.out.println("Add User - [ID: " + id + "] [PW: " + pw + "] [Name: " + name + "] [Level: " + level + "]");
		JSONObject obj = new JSONObject();
		obj.put("name", id);
		obj.put("fullName", name);
		obj.put("password", pw);
		obj.put("level", level);
		String inputString = obj.toString();
		
		String response = requestPOST("/addUser", inputString);
		System.out.println("Add USER: " + response);
		
		
	}
	
	//GET
	public String getCurrUserInfo(String key) {
		String response = requestGET("/userName/" + getCurrUser() + "");
		if(response != null) {
			//System.out.println("Response: " + response);
			JSONObject obj = new JSONObject(response);
			
			if(obj.has(key)) {
				return obj.get(key).toString();
			}
			else {
				System.out.println("Wrong Key [" + key + "]");
				return null;
			}
		}
		return null;
	}
	
	// Projects -------------------------------------------------------------------------------
	//GET
	public ArrayList<String> getProjects(){
		
		ArrayList<String> array = new ArrayList<String>();
		//ArrayList<String> projects = new ArrayList<String>(Arrays.asList("project1", "project2", "project3", "project4"));	// Change this -> Use model call
		
		
		try {
			JSONObject projectList = new JSONObject();
			
			URL url = new URL(urlString+"/listProject");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();		
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
	
			//connection.setRequestProperty("Content-Type", "application/json; utf-8");
            //connection.setRequestProperty("Accept", "application/json");
            
			 
			int responseCode = connection.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

			
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());
                JSONArray projects = new JSONArray(response.toString());
                
                // Parse ---------------------------------------------------------                
                for(int i = 0; i < projects.length(); i++) {
                	JSONObject obj = projects.getJSONObject(i);
                	System.out.println(obj.toString());
                	String projectTitle = obj.get("title").toString();
                	array.add(projectTitle);
                }
                System.out.println(array);
                return array;
                
            } else {
                System.out.println("Failed to GET PROJECTLIST");
                return null;
            }
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//Project add test -> use pre-defined variable
		/*
		for(String project : projects) {
			array.add(project);
		}		
		return array;	
		*/
	}
	
	//POST
	public void addProject(String title, String description, String pl, String dev, String tester) {
		System.out.println("[Title: " + title + "] [Description: " + description + "]" + "["+ pl + "/" + dev + "/" + tester +"]");
		
		try {
			
			URL url = new URL(urlString+"/addProject");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();		
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json;");
            connection.setRequestProperty("Accept", "application/json");
            
            // Make input data
			JSONObject project = new JSONObject();
			project.put("title", title);
			project.put("description", description);
			project.put("pl", pl);
			project.put("developer", dev);
			project.put("tester", tester);
			String inputString = project.toString();
			System.out.println(inputString);

            // Write input data
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = inputString.getBytes("utf-8");
                os.write(input, 0, input.length);           
            }
            // Check ACK
            int responseCode = connection.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            
            // Get Data
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String responseString = response.toString();
                
                System.out.println(responseString);
                if(responseString.equals("false")){
                	System.out.println("Project Already Exist");
                }
                
                
            } else {
                System.out.println("Failed to POST addProject");
            }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean getProjectFlag() {
		return projectSelectFlag;
	}
	
	public String getCurrProject() {
		return currProject;
	}
	
	public void projectSelect(String title){
		currProject = title;
		projectSelect();
	}	
	public void projectSelect() {
		setProjectFlag(true);
	}
	
	public void setProjectFlag(boolean flag) {
		projectSelectFlag = flag;
	}
	
	// Log in & out --------------------------------------------------------------------------	
	public boolean login(String id, String password){
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
	    				userID = id;
	    				mainWindow.setUser(userID);
	    				
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
		userID = "UNKNOWN";
		mainWindow.setUser(userID);
				
		setProjectFlag(false);	
		loginWindow.setVisible(true);
		mainWindow.setVisible(false);		
	}
	
	// Getters -------------------------------------------------------------------------------
	
	public String[][] getProjectContent(){
		String [][] content;
		try {
			JSONObject projectList = new JSONObject();
			
			URL url = new URL(urlString+"/listProject");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();		
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
	
			//connection.setRequestProperty("Content-Type", "application/json; utf-8");
            //connection.setRequestProperty("Accept", "application/json");
            
			 
			int responseCode = connection.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

			
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());
                JSONArray projects = new JSONArray(response.toString());
                
                // Parse --------------------------------------------------------- 
                
                
                String[] keys = {"title", "description", "date"};
                content = extractValues(response.toString(), keys);
                System.out.println(content);
                return content;
                
            } else {
                System.out.println("Failed to GET PROJECTLIST");
                return null;
            }
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String> getPriorties(){
		ArrayList<String> array = new ArrayList<String>();		
		ArrayList<String> priority = new ArrayList<String>(Arrays.asList( "Major", "Minor", "Blocker", "Critical", "Trivial"));	// Change this -> Use model call
		
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
		ArrayList<String> milestones = new ArrayList<String>(Arrays.asList("New", "Assigned", "Fixed", "Resolved", "Closed", "Reopened"));	// Change this -> Use model call
		
		for(String stone : milestones) {
			array.add(stone);
		}
		return array;
	}
	
	public ArrayList<String> getLevelString(){
		/* Return levels into String list -> Used in adding new users
		 * */
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> levels = new ArrayList<String>(Arrays.asList("Admin", "PL", "Dev", "Tester"));		// Change this -> Use model call
		
		for(String level : levels) {
			array.add(level);
		}
		return array;
	}
	
	public ArrayList<String> getDummyTicket() {
		ArrayList<String> ticket = new ArrayList<String>();
		return ticket;
	}
	
	//GET
	public ArrayList<String> getUserByLevel(int level){
		/* -1 -> return all users except admin
		 *  0 -> admin
		 *  1 -> pl
		 *  2 -> dev
		 *  3 -> tester			
		*/
		try {
			JSONObject projectList = new JSONObject();
			
			URL url = new URL(urlString+"/listUser");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();		
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
	
			//connection.setRequestProperty("Content-Type", "application/json; utf-8");
            //connection.setRequestProperty("Accept", "application/json");
            
			 
			int responseCode = connection.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

			
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
                
                // After get response ------------------------------
                
                JSONArray array = new JSONArray(response.toString());
                ArrayList<String> allArr = new ArrayList<String>();
                ArrayList<String> adminArr = new ArrayList<String>();
                ArrayList<String> plArr = new ArrayList<String>();
                ArrayList<String> devArr = new ArrayList<String>();
                ArrayList<String> testerArr = new ArrayList<String>();
                

                for(int i = 0; i < array.length(); i++) {
                	JSONObject obj = array.getJSONObject(i);
                	int lv = obj.getInt("level");
                	
                	switch (lv) {
                    case 0:
                        adminArr.add(obj.get("name").toString());
                        break;
                    case 1:
                        plArr.add(obj.get("name").toString());
                        allArr.add(obj.get("name").toString());
                        break;
                    case 2:
                        devArr.add(obj.get("name").toString());
                        allArr.add(obj.get("name").toString());
                        break;
                    case 3:
                        testerArr.add(obj.get("name").toString());
                        allArr.add(obj.get("name").toString());
                        break;
                	}
                }                
                System.out.println(adminArr);
                System.out.println(plArr);
                System.out.println(devArr);
                System.out.println(testerArr);
                
               switch(level) {
               case -1:
            	   return allArr;
               case 0:
            	   return adminArr;
               case 1:
            	   return plArr;
               case 2:
            	   return devArr;
               case 3:
            	   return testerArr;
               default:
            	   return null;
               }
                
            } else {
                System.out.println("Failed to GET PROJECTLIST");
            }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
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
	}*/
	
	// General Usage ===========================================================================================
	 public String[][] extractValues(String jsonString, String[] keys) {
	        
		 	JSONArray jsonArray = new JSONArray(jsonString);
		 	
	        // init array
	        String[][] valuesArray = new String[jsonArray.length()][keys.length];

	        // JSON parse
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject jsonObject = jsonArray.getJSONObject(i);
	            String[] values = new String[keys.length];

	            // extract value for keys
	            for (int j = 0; j < keys.length; j++) {
	                values[j] = jsonObject.optString(keys[j], ""); // return null if value is not there
	            }
	            valuesArray[i] = values;
	            System.out.print("EXTRACT["+i+"]: ");
	            System.out.println(Arrays.toString(values));
	        }

	        return valuesArray;
	    }
	
	 public String requestGET(String postfix){
			try {
				URL url = new URL(urlString + postfix);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();		
				connection.setRequestMethod("GET");
				connection.setDoOutput(true);
		
				//connection.setRequestProperty("Content-Type", "application/json; utf-8");
	            //connection.setRequestProperty("Accept", "application/json");
	            
				 
				int responseCode = connection.getResponseCode();
	            System.out.println("GET Response Code :: " + responseCode);

				
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String inputLine;
	                StringBuilder response = new StringBuilder();

	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                in.close();
	                //System.out.println(response.toString());
	                return response.toString();
	            } else {
	                System.out.println("Failed to GET REQUEST - [" + url + "]");
	                return null;
	            }
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	 }
	 
	 public String requestPOST(String postfix, String inputString) {
		 try {
				URL url = new URL(urlString + postfix);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();		
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json;");
	            connection.setRequestProperty("Accept", "application/json");

				System.out.println(inputString);

	            // Write input data
	            try(OutputStream os = connection.getOutputStream()) {
	                byte[] input = inputString.getBytes("utf-8");
	                os.write(input, 0, input.length);           
	            }
	            // Check ACK
	            int responseCode = connection.getResponseCode();
	            System.out.println("GET Response Code :: " + responseCode);
	            
	            // Get Data
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
	                String inputLine;
	                StringBuilder response = new StringBuilder();

	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                in.close();

	                String responseString = response.toString();
	                System.out.println(responseString);
	                return responseString;
	                
	                
	            } else {
	                System.out.println("Failed to POST REQUEST - [" + url + "], [" + inputString + "]");
	            }
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		 return null;
	 }
	 
	 public enum Priority {
		 MAJOR, MINOR, BLOCKER, CRITICAL, TRIVIAL
	 } // TODO Is Order of ENUM Ok?
	 public enum Status {
		 NEW, ASSIGNED, FIXED, RESOLVED, CLOSED, REOPENED
	 }
	 public enum Type {
		 BUG, TASK
	 }
	 
	// For testing ==============================================================================================
	private void GETtest() {
		
	}
}
