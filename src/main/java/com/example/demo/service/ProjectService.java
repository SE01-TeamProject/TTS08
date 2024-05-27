package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.domain.Issue;
import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	private final MemberRepository memberRepository;

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
			projectRepository.save(new Project(projectAddDto.getTitle(), 
					projectAddDto.getDescription(), 
					memberRepository.findByName(projectAddDto.getPL()).getId(), 
					memberRepository.findByName(projectAddDto.getDeveloper()).getId(),
					memberRepository.findByName(projectAddDto.getTester()).getId()));
		}
		return "true";
	}
	
//	public List<Project> getProjectList(){
//        List<Project> projects = new ArrayList<Project>();
//        projectRepository.findAll().forEach(project->projects.add(project));
//        return projects;
//	}
	
	public String getProjectList() {
		JSONArray projects = new JSONArray();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		projectRepository.findAll().forEach(item -> {
			JSONObject obj = new JSONObject();
			Optional<Member> user;
			obj.put("title", item.getTitle());
			obj.put("description", item.getDescription());
			System.out.println(">>> " + obj.toString());
			user = memberRepository.findById(item.getPL());
			obj.put("PL", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getDeveloper());
			obj.put("developer", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getTester());
			obj.put("tester", user.isEmpty() ? "N/A" : user.get().getName());
			obj.put("date", item.getDate().format(formatter));
			projects.put(obj);
		});
		return projects.toString();
	}

	public List<ProjectDto> getAllProjects() {  //project 조회
		return projectRepository.findAll().stream()
				.map(ProjectDto::from)
				.collect(Collectors.toList());
	}
}
