package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectAddDto;
import com.example.demo.domain.Issue;
import com.example.demo.domain.Project;
import com.example.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	
	public Project findById(Integer id) {
		return projectRepository.findById(id).get();
	}
	
	public String addProject(ProjectAddDto projectAddDto) {
System.out.println(">>> Project added " + projectAddDto.getTitle());
		Project project = projectRepository.findByTitle(projectAddDto.getTitle());
		if (project != null) {
System.out.println(">>> Project add fail");
			return "false";
		} else {
			projectRepository.save(new Project(projectAddDto.getTitle(), projectAddDto.getDescription()));
System.out.println(">>> Project added OK");
		}
		return "true";
	}
	
}
