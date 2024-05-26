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
		Project project = projectRepository.findByTitle(projectAddDto.getTitle());
		if (project != null) {
			return "false";
		} else {
			projectRepository.save(new Project(projectAddDto.getTitle(), projectAddDto.getDescription()));
		}
		return "true";
	}
	
}
