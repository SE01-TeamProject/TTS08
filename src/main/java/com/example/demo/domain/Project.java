package com.example.demo.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
public class Project {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
	private Long id;
	private String title;
	private List<PL> pls;
	private List<Admin> admins;
	private List<Tester> testers;
	private List<Developer> developers;
	private String log;
	
	public Project() {
    }

    public Project(Long id, String title, List<PL> pls, List<Admin> admins, List<Tester> testers, List<Developer> developers, String log) {
    	this.id = id;
        this.title = title;
        this.pls = pls;
        this.admins = admins;
        this.testers = testers;
        this.developers = developers;
        this.log = log;
    }
    
    public void addPL(PL pls) {}
    
    public void addTester(Tester tester) {}
    
    public void addDeveloper(Developer developer) {}
    
    public void removePL(PL pls) {}
    
    public void removeTester(Tester tester) {}
    
    public void removeDeveloper(Tester tester) {}
    
    public void logActivity(String log) {}
    
    public PL getPLs() {
    	return null;
    }
    
    public Tester getTesters() {
    	return null;
    }
    
    public Developer getDevelopers() {
    	return null;
    }
}
