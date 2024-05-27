package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import org.springframework.ui.Model;
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

//	@GetMapping("/project/{id}")
//	public Project getProject(@PathVariable("id") int id) {
//	    return ps.getProjectById(id);
//	}

//	@GetMapping("/listProject")
//	public List<Project> getProjectList() {
//		return ps.getProjectList();
//	}
	
	@GetMapping("/listProject")
	public String getProjectList() {
		return ps.getProjectList();
	}

	@GetMapping("/project")  //프로젝트 조회
	public ResponseEntity<List<ProjectDto>> getAllProjects() {
		List<ProjectDto> projects = ps.getAllProjects();
		return ResponseEntity.ok(projects);
	}
}
