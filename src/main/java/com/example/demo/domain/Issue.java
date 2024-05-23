package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

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
public class Issue {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_title")
	private String title;		// 이슈 제목
	private Date date;			// 이슈 작성 날짜
	private String description;	// 이슈 내용
	private Tester reporter;	// 이슈 발견자
	private Developer assignee;	// 이슈 해결자
	private int priority;		// 이슈 우선순위
	private int status;			// 이슈 상태
	private int type;			// 이슈 형태
	private Comment comments;	// 이슈의 코멘트
	
	public Issue() {
    }

    public Issue(String title, Date date, String description, Tester reporter, Developer assignee, int priority, int status, int type, Comment comments) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.reporter = reporter;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
        this.type = type;
        this.comments = comments;
    }
    
    public void addComment(Comment comment) {}
    
    public void changeStatus(int status) {}
    
    public void assignDeveloper(Developer developer) {}
}
