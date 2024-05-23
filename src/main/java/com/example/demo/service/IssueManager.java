package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Developer;
import com.example.demo.domain.Issue;
import com.example.demo.domain.Tester;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class IssueManager {
	private Developer rec;
	private Issue issues;
	
	public Issue createIssue(String title, String description, Tester reporter) {
		Issue issue = null;
		return issue;
	}
	
	public void assignIssue(Issue issue, Developer developer) {}
	
	public void changeIssueStatus(Issue issue, int status) {}
	
	public void addComment(Issue issue, Comment comment) {}
	
	public Issue getIssueById(int issueId) {
		return null;
	}
}
