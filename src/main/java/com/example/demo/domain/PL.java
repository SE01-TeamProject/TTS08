package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.Builder;

//@Entity
//@Builder
public class PL extends User {
    public PL() {
    }

    public PL(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
    
    public void manageDevelopers(Developer developer) {}
    public void manageTesters(Tester tester) {}
}
