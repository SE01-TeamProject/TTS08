package com.example.demo.domain;

import org.springframework.stereotype.Component;

@Component
public class Admin extends User {
    public Admin() {
    }

    public Admin(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
}