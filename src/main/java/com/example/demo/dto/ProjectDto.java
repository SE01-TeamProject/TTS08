package com.example.demo.dto;

import com.example.demo.domain.Project;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectDto {
	
	private String title;
	private String description;

	public static ProjectDto from(Project project) {
    	return new ProjectDto(project.getTitle(), project.getDescription());
    }
    
    public static ProjectDto of(String title, String description) {
    	return new ProjectDto(title, description);
    }
}
