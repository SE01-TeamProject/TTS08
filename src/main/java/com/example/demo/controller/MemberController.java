package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberLoginDto;
import com.example.demo.service.MemberService;

@RestController
public class MemberController {

	private final MemberService ms;
	
	@Autowired
	public MemberController(MemberService ms) {
		this.ms = ms;
	}
	
	// 로그인 성공여부 판별
	@PostMapping("/login")
	public String login(@RequestBody MemberLoginDto memberLoginDto) {
		return ms.login(memberLoginDto);
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody MemberAddDto memberAddDto) {
		return ms.addUser(memberAddDto);
	}
	
	@GetMapping("/listUser")	// admin의 User 나열
	public List<Member> getMemberList() {
		return ms.getMemberList();
	}
	
	@GetMapping("/user/{id}")	// 고유 id를 받아 유저 정보 불러옴
	public String getMember(@PathVariable("id") Integer id) {
		return ms.getMember(id);
	}
	
	@GetMapping("/userName/{name}")	// 로그인 id를 받아 유저 정보 불러옴
	public String getMember(@PathVariable("name") String name) {
		return ms.getMember(name);
	}
	
//	@GetMapping("/getLevel/{id}")	// 사용자의 레벨만 가져오기
//	public String getMemberLevel(@PathVariable("id") Integer id) {
//		return ms.getMemberLevel(id);
//	}
}
