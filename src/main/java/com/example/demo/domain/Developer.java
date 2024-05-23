package com.example.demo.domain;

import org.springframework.stereotype.Component;

@Component
public class Developer extends User {
    public Developer() {
    }
    private Issue assignedIssue;

    public Developer(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
    
    public Issue getAssignedIssue() {
        return assignedIssue;
    }

    public void setAssignedIssue(Issue assignedIssue) {
        this.assignedIssue = assignedIssue;
    }
}
