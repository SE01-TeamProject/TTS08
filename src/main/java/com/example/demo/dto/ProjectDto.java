package com.example.demo.dto;

import com.example.demo.domain.Project;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectDto {

	private String title;
	private String description;
	private LocalDateTime date;

	public static ProjectDto from(Project project) {
		return new ProjectDto(project.getTitle(), project.getDescription(), project.getDate());
	}

	public static ProjectDto of(String title, String description, LocalDateTime date) {
		return new ProjectDto(title, description, date);
	}
}
