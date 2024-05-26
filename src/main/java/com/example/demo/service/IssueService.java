package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Issue;
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
}
