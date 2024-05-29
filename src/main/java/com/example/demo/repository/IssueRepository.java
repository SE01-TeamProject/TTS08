package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
	Optional<Issue> findById(@Param("id") Integer id);
	Issue findByTitle(@Param("title") String title);
	Optional<Issue> findByPriority(@Param("priority") int priority);
	Optional<Issue> findByStatus(@Param("status") int status);
	Optional<Issue> findByType(@Param("type") int type);
	Optional<Issue> findByReporter(@Param("reporter") Integer reporter);
	Optional<Issue> findByAssignee(@Param("assignee") Integer assignee);
	long countByPriority(int priority);
	long countByStatus(int status);
	long countByType(int type);
	//List<Comment> findAllByProjectId(Iterable<Integer> id);
}
