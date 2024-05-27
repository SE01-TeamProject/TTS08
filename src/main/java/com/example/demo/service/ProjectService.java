package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.domain.Issue;
import com.example.demo.domain.Project;
import com.example.demo.repository.ProjectRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	
	public Project findById(Integer id) {
		return projectRepository.findById(id).get();
	}
	
//	public Project getProjectById(Integer id) {
//		return projectRepository.findById(id).get();
//	}
	
	public String addProject(ProjectAddDto projectAddDto) {
		Project project = projectRepository.findByTitle(projectAddDto.getTitle());
		if (project != null) {
			return "false";
		} else {
			projectRepository.save(new Project(projectAddDto.getTitle(), projectAddDto.getDescription()));
		}
		return "true";
	}
	
	public List<Project> getProjectList(){
        List<Project> projects = new ArrayList<Project>();
        projectRepository.findAll().forEach(project->projects.add(project));
        return projects;
	}
}
