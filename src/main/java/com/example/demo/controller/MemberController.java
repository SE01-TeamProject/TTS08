package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberLoginDto;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	private final MemberService ms;
	
	@Autowired
	public MemberController(MemberService ms) {
		this.ms = ms;
	}
	
	@GetMapping("/")
	public String index() {
		return "redirect:/login.html";
	}
	
	// 로그인 페이지 요청
	@GetMapping("login")
	public String loginForm() {
		return "member/login";
	}
	
	// 로그인 성공여부 판별
	@PostMapping("member/login")
	public String login(@ModelAttribute MemberLoginDto memberLoginDto, HttpSession session) {
		if (ms.login(memberLoginDto)) {
			session.setAttribute("loginName", memberLoginDto.getName());
			return "redirect:/home.html";
		} else {
			return "redirect:/login.html";
		}
	}
	
}
