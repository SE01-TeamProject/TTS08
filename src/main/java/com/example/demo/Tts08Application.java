package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;

@SpringBootApplication
public class Tts08Application {

	public static void main(String[] args) {
		//SpringApplication.run(Tts08Application.class, args);

		// 테스트옹 코드
		ConfigurableApplicationContext context = SpringApplication.run(Tts08Application.class, args);
		MemberRepository memberRepository = context.getBean(MemberRepository.class);
		
		memberRepository.save(new Member("admin", "Administator", "admin", 0));
		Member member = memberRepository.findByName("admin");
		System.out.println("name: " + member.getName() + ", fullname: " + member.getFullName() + ", level: " + member.getLevel());
		
		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
		
		projectRepository.save(new Project("Project1", "This is Project1"));
		Project project = projectRepository.findByTitle("Project1");
		System.out.println("title: " + project.getTitle() + ", description: " + project.getDescription());
	}

}
