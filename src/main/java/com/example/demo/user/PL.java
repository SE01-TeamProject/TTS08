package com.example.demo.user;

import org.springframework.stereotype.Component;

@Component
public class PL extends User {
    public PL() {
    }

    public PL(String id, String name, String password, int level) {
        super(id, name, password, level);
    }
}
