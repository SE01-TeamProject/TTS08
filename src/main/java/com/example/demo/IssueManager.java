package com.example.demo;

import com.example.demo.user.Developer;

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
