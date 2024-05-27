package com.example.demo.domain;

import com.example.demo.domain.mapping.MemberAssignedPr;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime date;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Issue> issues = new ArrayList<>();

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Log> logs = new ArrayList<>();

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<MemberAssignedPr> userAssignedPrs = new ArrayList<>();

	@Builder
    public Project(String title, String description, Integer PL, Integer developer, Integer tester) {
        this.title = title;
        this.description = description;
        this.PL = PL;
        this.developer = developer;
        this.tester = tester;
        this.date = LocalDateTime.now();
    }
	
	public static Project createProject(String title, String description, Integer PL, Integer developer, Integer tester) {
		Project project = new Project(title, description, PL, developer, tester);
    	return project;
    }
}
