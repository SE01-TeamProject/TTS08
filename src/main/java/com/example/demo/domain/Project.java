package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@NoArgsConstructor
@Getter
public class Project {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Integer id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PL")
	private Integer PL;
	
	@Column(name = "DEVELOPER")
	private Integer developer;
	
	@Column(name = "TESTER")
	private Integer tester;
	
	@Builder
    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }
	
	public static Project createProject(String title, String description) {
		Project project = new Project(title, description);
    	return project;
    }
}
