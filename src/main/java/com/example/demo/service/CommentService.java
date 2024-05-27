package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Project;
import com.example.demo.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	public Comment findById(Integer id) {
		return commentRepository.findById(id).get();
	}
	
	public List<Comment> getCommentList(){
        List<Comment> comments = new ArrayList<Comment>();
        commentRepository.findAll().forEach(comment->comments.add(comment));
        return comments;
	}
}
