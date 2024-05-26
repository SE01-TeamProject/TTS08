package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
}
