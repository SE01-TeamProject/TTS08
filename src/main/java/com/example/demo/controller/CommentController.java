package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Project;
import com.example.demo.service.CommentService;
import com.example.demo.service.IssueService;

@RestController
public class CommentController {
	
	private final CommentService cs;

	@Autowired
	public CommentController(CommentService cs) {
		this.cs = cs;
	}
	
	@GetMapping("/listComment")
	public List<Comment> getCommentList() {
		return cs.getCommentList();
	}
}
