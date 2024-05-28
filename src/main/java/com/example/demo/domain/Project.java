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
	private Integer id;			// 프로젝트 고유 id값
	
	@Column(name = "TITLE")
	private String title;		// 프로젝트 제목
	
	@Column(name = "DESCRIPTION")
	private String description;	// 프로젝트 내용
	
	@Column(name = "PL")
	private Integer PL;			// 프로젝트에 할당된 PL - 1명만
	
	@Column(name = "DEVELOPER")
	private Integer developer;	// 프로젝트에 할당된 개발자 - 1명만
	
	@Column(name = "TESTER")
	private Integer tester;		// 프로젝트에 할당된 테스터 - 1명만

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime date;	// 프로젝트 생성 날짜

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
