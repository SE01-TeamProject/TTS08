package com.example.demo.service;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Developer;

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
