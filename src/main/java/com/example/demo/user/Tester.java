package com.example.demo.user;

import org.springframework.stereotype.Component;

@Component
public class Tester extends User {
    public Tester() {
    }

    public Tester(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
}
