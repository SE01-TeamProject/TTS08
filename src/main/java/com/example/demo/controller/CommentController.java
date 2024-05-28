package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Comment;
import com.example.demo.dto.CommentAddDto;
import com.example.demo.service.CommentService;

@RestController
public class CommentController {
	
	private final CommentService cs;

	@Autowired
	public CommentController(CommentService cs) {
		this.cs = cs;
	}
	
	// 코멘트들을 가져와 나열하는 메소드
	@GetMapping("/listComment")
	public List<Comment> getCommentList() {
		return cs.getCommentList();
	}
	
	@GetMapping("/listComment/{id}")	// 이슈 Id로 코멘트 나열
	public String getCommentList(@PathVariable("id") Integer id) {
		return cs.getCommentList(id);
	}
	
	// 코멘트를 추가하는 메소드
	@PostMapping("/addComment")
	public String addComment(@RequestBody CommentAddDto commentAddDto) {
		return cs.addComment(commentAddDto);
	}
	
	@GetMapping("/comment/{id}")	// 고유 id를 받아 코멘트 정보 불러옴
	public String getComment(@PathVariable("id") Integer id) {
		return cs.getComment(id);
	}
}
