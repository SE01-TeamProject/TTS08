package com.example.demo.service;

import org.springframework.stereotype.Service;

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
	
	public void addIssue(Project project, Issue issue) {}
}
