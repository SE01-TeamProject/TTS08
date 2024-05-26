package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
	Optional<Issue> findById(@Param("id") Integer id);
	Issue findByTitle(@Param("title") String title);
}
