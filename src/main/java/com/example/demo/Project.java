package com.example.demo;

import org.springframework.stereotype.Component;

import com.example.demo.user.Admin;
import com.example.demo.user.Developer;
import com.example.demo.user.PL;
import com.example.demo.user.Tester;

@Component
public class Project {
	private String title;
	private PL pls;
	private Admin admin;
	private Tester testers;
	private Developer developers;
	private String log;
	
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PL getPls() {
        return pls;
    }

    public void setPls(PL pls) {
        this.pls = pls;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Tester getTesters() {
        return testers;
    }

    public void setTesters(Tester testers) {
        this.testers = testers;
    }

    public Developer getDevelopers() {
        return developers;
    }

    public void setDevelopers(Developer developers) {
        this.developers = developers;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
