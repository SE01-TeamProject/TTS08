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
	
	@Column(name = "DEVELOPER1")
	private Integer developer1;	// 프로젝트에 할당된 개발자
	@Column(name = "DEVELOPER2")
	private Integer developer2;	// 프로젝트에 할당된 개발자
	@Column(name = "DEVELOPER3")
	private Integer developer3;	// 프로젝트에 할당된 개발자
	
	@Column(name = "TESTER1")
	private Integer tester1;		// 프로젝트에 할당된 테스터
	@Column(name = "TESTER2")
	private Integer tester2;		// 프로젝트에 할당된 테스터
	@Column(name = "TESTER3")
	private Integer tester3;		// 프로젝트에 할당된 테스터

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
    public Project(String title, String description, Integer PL, Integer developer1, Integer developer2, Integer developer3, Integer tester1, Integer tester2, Integer tester3) {
        this.title = title;
        this.description = description;
        this.PL = PL;
        this.developer1 = developer1;
        this.developer2 = developer2;
        this.developer3 = developer3;
        this.tester1 = tester1;
        this.tester2 = tester2;
        this.tester3 = tester3;
        this.date = LocalDateTime.now();
    }
	
	public static Project createProject(String title, String description, Integer PL, Integer developer1, Integer developer2, Integer developer3, Integer tester1, Integer tester2, Integer tester3) {
		Project project = new Project(title, description, PL, developer1, developer2, developer3, tester1, tester2, tester3);
    	return project;
    }
}
