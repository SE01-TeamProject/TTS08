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
		if(memberRepository.count() == 0) {
			memberRepository.save(new Member("admin", "Administator", "admin", 0));

			memberRepository.save(new Member("PL1", "PL1user", "pl1", 1));
			memberRepository.save(new Member("PL2", "PL2user", "pl2", 1));
			memberRepository.save(new Member("PL3", "PL3user", "pl3", 1));

			memberRepository.save(new Member("Dev1", "Dev1user", "dev1", 2));
			memberRepository.save(new Member("Dev2", "Dev2user", "dev2", 2));
			memberRepository.save(new Member("Dev3", "Dev3user", "dev3", 2));

			memberRepository.save(new Member("QA1", "QA1user", "qa1", 3));
			memberRepository.save(new Member("QA2", "QA2user", "qa2", 3));
			memberRepository.save(new Member("QA3", "QA3user", "qa3", 3));
		}

		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

		if(projectRepository.count() == 0) {
			projectRepository.save(new Project("Project1", "This is Project1", 0, 0, 0));
		}
		Project project = projectRepository.findByTitle("Project1");
		System.out.println("title: " + project.getTitle() + ", description: " + project.getDescription());
		
		IssueRepository issueRepository = context.getBean(IssueRepository.class);
		if(issueRepository.count() == 0) {
			issueRepository.save(new Issue(1, "Issue1", "This is Issue1", 0, 0, 0, 0, 0));
			issueRepository.save(new Issue(1, "Issue2", "This is Issue2", 0, 0, 0, 0, 0));
		}

		Issue issue1 = issueRepository.findByTitle("Issue1");
		System.out.println("title: " + issue1.getTitle() + ", description: " + issue1.getDescription());

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
