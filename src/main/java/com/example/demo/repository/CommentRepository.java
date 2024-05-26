package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Optional<Comment> findById(@Param("id") Integer id);
}
