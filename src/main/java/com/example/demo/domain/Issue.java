package com.example.demo.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Issue {
	private Date date;
	private String title;
	private String description;
	private Tester reporter;
	private Developer assignee;
	private int priority;
	private int status;
	private int type;
	private Comment comments;
	
	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tester getReporter() {
        return reporter;
    }

    public void setReporter(Tester reporter) {
        this.reporter = reporter;
    }

    public Developer getAssignee() {
        return assignee;
    }

    public void setAssignee(Developer assignee) {
        this.assignee = assignee;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }
}
