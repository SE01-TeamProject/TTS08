package com.example.demo.service;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Developer;
import com.example.demo.domain.Issue;

@Component
public class IssueManager {
	private Developer rec;
	private Issue issues;
	
	public Developer getRec() {
        return rec;
    }

    public void setRec(Developer rec) {
        this.rec = rec;
    }

    public Issue getIssues() {
        return issues;
    }

    public void setIssues(Issue issues) {
        this.issues = issues;
    }
}
