package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProjectAddDto;
import com.example.demo.service.ProjectService;

@RestController
public class ProjectController {
	
	private final ProjectService ps;

	@Autowired
	public ProjectController(ProjectService ps) {
		this.ps = ps;
	}
	
	@PostMapping("/addProject")
	public String addProject(@RequestBody ProjectAddDto projectAddDto) {
System.out.println(">>> ProjectController::addProject");
		return ps.addProject(projectAddDto);
	}
	
}
