package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.Builder;

@Entity
@Builder
public class Admin extends User {
    public Admin() {
    }

    public Admin(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
    
    public void manageProject(Project project) {}
}
