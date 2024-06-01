package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.IssueSetDto;
import com.example.demo.service.IssueService;

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
	
	// 우선순위와 상태, 담당자를 변경하는 메소드
	@PostMapping("/setIssue")
	public void setState(@RequestBody IssueSetDto issueSetDto) {
		is.setState(issueSetDto);
	}
	
	@GetMapping("/issue/{id}")	// 고유 id를 받아 유저 정보 불러옴
	public String getIssue(@PathVariable("id") Integer id) {
		return is.getIssue(id);
	}

 	@GetMapping("/issueTitle/{title}")
	public String getIssueTitle(@PathVariable("title") String title) {
		return is.getIssueTitle(title);
	}
	
	@GetMapping("/issuePriority/{priority}")
	public String getIssuePriority(@PathVariable("priority") String priority) {
		return is.getIssuePriority(priority);
	}
	
	@GetMapping("/issueStatus/{status}")
	public String getIssueStatus(@PathVariable("status") String status) {
		return is.getIssueStatus(status);
	}
	
	@GetMapping("/issueType/{type}")
	public String getIssueType(@PathVariable("type") String type) {
		return is.getIssueType(type);
	}
	
	@GetMapping("/issueReporter/{reporter}")
	public String getIssueReporter(@PathVariable("reporter") String reporter) {
		return is.getIssueReporter(reporter);
	}
	
	@GetMapping("/issueAssignee/{assignee}")
	public String getIssueAssignee(@PathVariable("assignee") String assignee) {
		return is.getIssueAssignee(assignee);
	}
	
	// Assignee를 변경하는 메소드
//	@PostMapping("/setAssignee")
//	public void setAssignee(@RequestBody IssueSetDto issueSetDto) {
//		is.setAssignee(issueSetDto);
//	}
	
	@GetMapping("/issueStatics")
	public String getIssueStatics() {
		return is.getIssueStatics();
	}
	
	@GetMapping("/issueTrend")
	public String getIssueTrend() { return is.getIssueTrend(); }
	
	@GetMapping("/suggestAssignee/{desc}")
	public String suggestAssignee(@PathVariable("desc") String description) {
		return is.suggestAssignee(description);
	}
}
