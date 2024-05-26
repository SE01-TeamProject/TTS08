package com.example.demo.controller;

import com.example.demo.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProjectAddDto;
import com.example.demo.service.ProjectService;

import java.util.List;

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

	@GetMapping("/project")  //프로젝트 조회
	public ResponseEntity<List<ProjectDto>> getAllProjects() {
		List<ProjectDto> projects = ps.getAllProjects();
		return ResponseEntity.ok(projects);
	}
}
