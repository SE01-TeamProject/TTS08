package com.example.demo.domain;

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
public class Developer extends User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assigned_issue")
    private Issue assignedIssue;
    
    public Developer() {
    }

    public Developer(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
}
