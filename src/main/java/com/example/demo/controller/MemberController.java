package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.MemberLoginDto;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;

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
	
	
}
