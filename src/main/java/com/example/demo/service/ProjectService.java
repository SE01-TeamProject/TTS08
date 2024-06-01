package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	private final MemberRepository memberRepository;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public Project findById(Integer id) {
		return projectRepository.findById(id).get();
	}

	//특정 프로젝트의 Id를 받는 메소드
	public String getProjectId(String title) {
		int testProjectId= projectRepository.findByTitle(title).getId();
		return String.valueOf(testProjectId);
	}

	// 프로젝트를 추가하는 메소드
	public String addProject(ProjectAddDto projectAddDto) {
		Project project = projectRepository.findByTitle(projectAddDto.getTitle());
		if (project != null) {
			return "false";
		} else {
			projectRepository.save(new Project(projectAddDto.getTitle(), 
					projectAddDto.getDescription(), 
					memberRepository.findByName(projectAddDto.getPL()).getId(), 
					memberRepository.findByName(projectAddDto.getDeveloper1()).getId(),
					memberRepository.findByName(projectAddDto.getDeveloper2()).getId(),
					memberRepository.findByName(projectAddDto.getDeveloper3()).getId(),
					memberRepository.findByName(projectAddDto.getTester1()).getId(),
					memberRepository.findByName(projectAddDto.getTester2()).getId(),
					memberRepository.findByName(projectAddDto.getTester3()).getId()));
		}
		return "true";
	}
	
//	public List<Project> getProjectList(){
//        List<Project> projects = new ArrayList<Project>();
//        projectRepository.findAll().forEach(project->projects.add(project));
//        return projects;
//	}
	
	// 프로젝트들을 가져와 문자열 형식으로 나열하는 메소드
	public String getProjectList() {
		JSONArray projects = new JSONArray();
		projectRepository.findAll().forEach(item -> {
			JSONObject obj = new JSONObject();
			Optional<Member> user;
			obj.put("id", item.getId());
			obj.put("title", item.getTitle());
			obj.put("description", item.getDescription());
			user = memberRepository.findById(item.getPL());
			obj.put("PL", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getDeveloper1());
			obj.put("developer1", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getDeveloper2());
			obj.put("developer2", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getDeveloper3());
			obj.put("developer3", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getTester1());
			obj.put("tester1", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getTester2());
			obj.put("tester2", user.isEmpty() ? "N/A" : user.get().getName());
			user = memberRepository.findById(item.getTester3());
			obj.put("tester3", user.isEmpty() ? "N/A" : user.get().getName());
			obj.put("date", item.getDate().format(formatter));
			projects.put(obj);
		});
		return projects.toString();
	}
	
	public String getProjectList(String userName) {
		JSONArray projects = new JSONArray();
		projectRepository.findAll().forEach(item -> {
			Optional<Member> PL = memberRepository.findById(item.getPL());
			Optional<Member> dev1 = memberRepository.findById(item.getDeveloper1());
			Optional<Member> dev2 = memberRepository.findById(item.getDeveloper2());
			Optional<Member> dev3 = memberRepository.findById(item.getDeveloper3());
			Optional<Member> test1 = memberRepository.findById(item.getTester1());
			Optional<Member> test2 = memberRepository.findById(item.getTester2());
			Optional<Member> test3 = memberRepository.findById(item.getTester3());
			if ((!PL.isEmpty() && userName.equals(PL.get().getName())) || 
				(!dev1.isEmpty() && userName.equals(dev1.get().getName())) ||
				(!dev2.isEmpty() && userName.equals(dev2.get().getName())) ||
				(!dev3.isEmpty() && userName.equals(dev3.get().getName())) ||
				(!test1.isEmpty() && userName.equals(test1.get().getName())) ||
				(!test2.isEmpty() && userName.equals(test2.get().getName())) ||
				(!test3.isEmpty() && userName.equals(test3.get().getName()))) {
				JSONObject obj = new JSONObject();
				obj.put("id", item.getId());
				obj.put("title", item.getTitle());
				obj.put("description", item.getDescription());
				obj.put("PL", PL.isEmpty() ? "N/A" : PL.get().getName());
				obj.put("developer1", dev1.isEmpty() ? "N/A" : dev1.get().getName());
				obj.put("developer2", dev2.isEmpty() ? "N/A" : dev2.get().getName());
				obj.put("developer3", dev3.isEmpty() ? "N/A" : dev3.get().getName());
				obj.put("tester1", test1.isEmpty() ? "N/A" : test1.get().getName());
				obj.put("tester2", test2.isEmpty() ? "N/A" : test2.get().getName());
				obj.put("tester3", test3.isEmpty() ? "N/A" : test3.get().getName());
				obj.put("date", item.getDate().format(formatter));
				projects.put(obj);
			}
		});
		return projects.toString();
	}
	
	// id를 받고 해당 프로젝트 정보를 가져오는 메소드
	public String getProject(Integer id) {
		Optional<Project> prj = projectRepository.findById(id);
		if (prj.isEmpty()) return "";
		
		JSONObject obj = new JSONObject();
		Optional<Member> user;
		obj.put("title", prj.get().getTitle());
		obj.put("description", prj.get().getDescription());
		user = memberRepository.findById(prj.get().getPL());
		obj.put("PL", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getDeveloper1());
		obj.put("developer1", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getDeveloper2());
		obj.put("developer2", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getDeveloper3());
		obj.put("developer3", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getTester1());
		obj.put("tester1", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getTester2());
		obj.put("tester2", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getTester3());
		obj.put("tester3", user.isEmpty() ? "N/A" : user.get().getName());
		obj.put("date", prj.get().getDate().format(formatter));
		return obj.toString();
	}

	//title을 받고 해당 프로젝트 정보를 가져오는 메소드
	public String getProjectByTitle(String title) {
		Optional<Project> prj = Optional.ofNullable(projectRepository.findByTitle(title));
		if (prj.isEmpty()) return "";

		JSONObject obj = new JSONObject();
		Optional<Member> user;
		obj.put("title", prj.get().getTitle());
		obj.put("description", prj.get().getDescription());
		user = memberRepository.findById(prj.get().getPL());
		obj.put("PL", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getDeveloper1());
		obj.put("developer1", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getDeveloper2());
		obj.put("developer2", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getDeveloper3());
		obj.put("developer3", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getTester1());
		obj.put("tester1", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getTester2());
		obj.put("tester2", user.isEmpty() ? "N/A" : user.get().getName());
		user = memberRepository.findById(prj.get().getTester3());
		obj.put("tester3", user.isEmpty() ? "N/A" : user.get().getName());
		obj.put("date", prj.get().getDate().format(formatter));
		return obj.toString();
	}

	public List<ProjectDto> getAllProjects() {  //project 조회
		return projectRepository.findAll().stream()
				.map(ProjectDto::from)
				.collect(Collectors.toList());
	}
}
