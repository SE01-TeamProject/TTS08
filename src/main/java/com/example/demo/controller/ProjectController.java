package com.example.demo.controller;

import com.example.demo.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		return ps.addProject(projectAddDto);
	}

	@GetMapping("/project")  //프로젝트 조회
	public ResponseEntity<List<ProjectDto>> getAllProjects() {
		List<ProjectDto> projects = ps.getAllProjects();
		return ResponseEntity.ok(projects);
	}
}
