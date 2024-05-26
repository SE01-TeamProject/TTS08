package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Issue;
import com.example.demo.dto.IssueAddDto;
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
	
	public void changeIssueStatus(Issue issue, int status) {
		issue.changeStatus(status);
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
}
