package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	public Comment findById(Integer id) {
		return commentRepository.findById(id).get();
	}
}
