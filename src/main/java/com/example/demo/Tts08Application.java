package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

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
	}

}
