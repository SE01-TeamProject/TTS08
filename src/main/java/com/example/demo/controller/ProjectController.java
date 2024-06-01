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

import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.ProjectDto;
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

	// 프로젝트를 추가하는 메소드
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
	
	// 프로젝트를 나열하는 메소드
	@GetMapping("/listProject")
	public String getProjectList() {
		return ps.getProjectList();
	}
	
	@GetMapping("/listProject/{uname}")
	public String getProjectList(@PathVariable("uname") String uname) {
		return ps.getProjectList(uname);
	}

	@GetMapping("/project")  //프로젝트 조회
	public ResponseEntity<List<ProjectDto>> getAllProjects() {
		List<ProjectDto> projects = ps.getAllProjects();
		return ResponseEntity.ok(projects);
	}
	
	@GetMapping("/project/{id}")	// 고유 id를 받아 프로젝트 정보 불러옴
	public String getProject(@PathVariable("id") Integer id) {
		return ps.getProject(id);
	}

	@GetMapping("/projectTitle/{title}")
	public String getProjectByTitle(@PathVariable("title") String title) { return ps.getProjectByTitle(title);}

	@GetMapping("/project/id/{title}") //특정 프로젝트의 title을 받아 그 프로젝트의 id를 반환
    public String getProjectIdByTitle(@PathVariable("title") String title) {return ps.getProjectId(title);}
}
