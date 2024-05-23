package com.example.demo.domain;

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
    @Column(name = "project_title")
	private String title;
	private PL pls;
	private Admin admin;
	private Tester testers;
	private Developer developers;
	private String log;
	
	public Project() {
    }

    public Project(String title, PL pls, Admin admin, Tester testers, Developer developers, String log) {
        this.title = title;
        this.pls = pls;
        this.admin = admin;
        this.testers = testers;
        this.developers = developers;
        this.log = log;
    }
}
