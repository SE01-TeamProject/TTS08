package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.domain.Issue;
import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.ui.SwingController;

@SpringBootApplication
public class Tts08Application {

	public static void main(String[] args) {
		//SpringApplication.run(Tts08Application.class, args);

		System.setProperty("java.awt.headless", "false");
		
		
		// 테스트옹 코드
		
		ConfigurableApplicationContext context = SpringApplication.run(Tts08Application.class, args);
		MemberRepository memberRepository = context.getBean(MemberRepository.class);
		
		memberRepository.save(new Member("admin", "Administator", "admin", 0));
		Member member = memberRepository.findByName("admin");
		System.out.println("name: " + member.getName() + ", fullname: " + member.getFullName() + ", level: " + member.getLevel());
		
		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
		
		projectRepository.save(new Project("Project1", "This is Project1", 0, 0, 0));
		Project project = projectRepository.findByTitle("Project1");
		System.out.println("title: " + project.getTitle() + ", description: " + project.getDescription());
		
		IssueRepository issueRepository = context.getBean(IssueRepository.class);
		
		issueRepository.save(new Issue("Issue1", "This is Issue1", 0, 0, 0, 0, 0));
		Issue issue1 = issueRepository.findByTitle("Issue1");
		System.out.println("title: " + issue1.getTitle() + ", description: " + issue1.getDescription());
		
		issueRepository.save(new Issue("Issue2", "This is Issue2", 0, 0, 0, 0, 0));
		Issue issue2 = issueRepository.findByTitle("Issue2");
		System.out.println("title: " + issue2.getTitle() + ", description: " + issue2.getDescription());
		
		
		try {
			SwingController sc = new SwingController();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
