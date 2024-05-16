package com.example.demo;

import org.springframework.stereotype.Component;

import com.example.demo.user.Developer;

@Component
public class Recommender {
	private Developer devs;
	
	public Developer getDevs() {
        return devs;
    }

    public void setDevs(Developer devs) {
        this.devs = devs;
    }
}
