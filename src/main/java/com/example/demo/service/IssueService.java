package com.example.demo.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Issue;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.IssueSetDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.IssueRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IssueService {
	private final IssueRepository issueRepository;
	private final CommentRepository commentRepository;
	
	public Issue findById(Integer id) {
		return issueRepository.findById(id).get();
	}
	
	public void setState(IssueSetDto issueSetDto) {
		Issue issue = issueRepository.findById(issueSetDto.getId()).get();
		issue.setState(issueSetDto.getPriority(), issueSetDto.getStatus());
		issueRepository.save(issue);
	}
	
	public void addComment(Issue issue, Comment comment) {
		Comment.createComment(issue.getId(), comment.getNote());
		commentRepository.save(comment);
	}
	
	public String addIssue(IssueAddDto issueAddDto) {
		Issue issue = issueRepository.findByTitle(issueAddDto.getTitle());
		if (issue != null) {
			return "false";
		} else {
			issueRepository.save(new Issue(issueAddDto.getTitle(), issueAddDto.getDescription()));
			//int priority = Issue.getPriorityFromString(issueAddDto.getPriority());
			//int status = Issue.getStatusFromString(issueAddDto.getStatus());
			//int type = Issue.getTypeFromString(issueAddDto.getType());
			//issueRepository.save(new Issue(issueAddDto.getTitle(), issueAddDto.getDescription(), issueAddDto.getReporter(), issueAddDto.getAssignee(), priority, status, type));
		}
		return "true";
	}
	
	// 그냥 이슈의 리스트 나열
//	public List<Issue> getIssueList() {
//		List<Issue> issues = new ArrayList<Issue>();
//		issueRepository.findAll().forEach(item -> issues.add(item));
//		return issues;
//	}
	
	public String getIssueList() {
		JSONArray issues = new JSONArray();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		issueRepository.findAll().forEach(item -> {
			JSONObject obj = new JSONObject();
			obj.put("title", item.getTitle());
			obj.put("description", item.getDescription());
			obj.put("date", item.getDate().format(formatter));
			issues.put(obj);
		});
		return issues.toString();
	}

	public String getIssueList(Integer id) {
		JSONArray issues = new JSONArray();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		issueRepository.findAll().forEach(item -> {
			if (item.getProject() == id) {
				JSONObject obj = new JSONObject();
				obj.put("title", item.getTitle());
				obj.put("description", item.getDescription());
				obj.put("date", item.getDate().format(formatter));
				issues.put(obj);
			}
		});
		return issues.toString();
	}
}
