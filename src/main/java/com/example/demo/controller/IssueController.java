package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Issue;
import com.example.demo.domain.Member;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.service.IssueService;
import com.example.demo.service.ProjectService;

@RestController
public class IssueController {
	
	private final IssueService is;

	@Autowired
	public IssueController(IssueService is) {
		this.is = is;
	}
	
	@PostMapping("/addIssue")
	public String addIssue(@RequestBody IssueAddDto issueAddDto) {
		return is.addIssue(issueAddDto);
	}
	
	@GetMapping("/listIssue")	// 그냥 이슈의 리스트 나열
	public String getIssueList() {
		return is.getIssueList();
	}
	
	@GetMapping("/listIssue/{id}")	// 프로젝트 Id로 이슈 나열
	public String getIssueList(@PathVariable("id") Integer id) {
		return is.getIssueList(id);
	}
}
