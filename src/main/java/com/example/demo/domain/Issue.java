package com.example.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "issue")
@NoArgsConstructor
@Getter
@Setter
public class Issue {
	
	public enum Priority {
		NORMAL, URGENT
	}
	
	public enum Status {
		OPEN, PROGRESS, CLOSED
	}
	
	public enum Type {
		BUG, TASK
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
	
	@Column(name = "TITLE")
	private String title;		// 이슈 제목
	
	@Column(name = "DESCRIPTION")
	private String description;	// 이슈 내용
	
	@Column(name = "REPORTER")
    private Integer reporter; // 이슈 발견자
	
	@Column(name = "ASSIGNEE")
    private Integer assignee; // 이슈 해결자
	
	@Column(name = "PRIORITY")
	private int priority;		// 이슈 우선순위
	
	@Column(name = "STATUS")
	private int status;			// 이슈 상태
	
	@Column(name = "TYPE")
	private int type;			// 이슈 형태

	@Column(name = "DATE")
    private LocalDateTime date; // 이슈 작성 날짜
	
	private Integer project;
	
	@Builder
    public Issue(String title, String description, Integer reporter, Integer assignee, int priority, int status, int type) {
        this.title = title;
        this.description = description;
        this.reporter = reporter;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
        this.type = type;
        this.date = LocalDateTime.now();
    }
    
	public static Issue createIssue(String title, String description, Integer reporter, Integer assignee, int priority, int status, int type) {
		Issue issue = new Issue(title, description, reporter, assignee, priority, status, type);
    	return issue;
    }
    
    public void changeStatus(int status) {
    	this.status = status;
    }
}
