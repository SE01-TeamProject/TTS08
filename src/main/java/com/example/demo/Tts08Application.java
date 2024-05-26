package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
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
		
		
		try {
			SwingController sc = new SwingController();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
