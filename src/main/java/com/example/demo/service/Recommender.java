package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Developer;
import com.example.demo.domain.Issue;

import lombok.Getter;
import lombok.Setter;

//@Component
//@Getter
//@Setter
public class Recommender {
	private List<Developer> devs;
	
	public List<Developer> recommendDevelopers(Issue issue) {
		return null;
	}
}
