package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Developer;
import com.example.demo.domain.PL;
import com.example.demo.domain.Project;
import com.example.demo.domain.Tester;
import com.example.demo.domain.User;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class System {
	private List<User> users;
	private List<Project> projects;
	
	public System(List<User> users, List<Project> projects) {
        this.users = users;
        this.projects = projects;
    }
	
	public void addUser(User user) {}
	
	public void removeUser(User user) {}
	
	public User getUser(String userId) {
		return null;
	}
	
	public Project createProject(String title, List<PL> pls, List<Admin> admins, List<Tester> testers, List<Developer> developers) {
		return null;
	}
	
	public Project getProject(Long id) {
		return null;
	}
	
	public List<Project> listProjects() {
		return null;
	}
	
	public List<User> listUsers() {
		return null;
		
	}
}
