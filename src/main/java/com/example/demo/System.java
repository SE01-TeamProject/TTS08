package com.example.demo;

import com.example.demo.user.User;

public class System {
	private User users;
	private Project projects;
	
	public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
    
    public Project getProjects() {
        return projects;
    }

    public void setProjects(Project projects) {
        this.projects = projects;
    }
}
