package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.Builder;

@Entity
@Builder
public class Tester extends User {
    public Tester() {
    }

    public Tester(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
}
