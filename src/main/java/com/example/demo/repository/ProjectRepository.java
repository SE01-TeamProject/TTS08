package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Optional<Project> findById(@Param("id") Integer id);
	Project findByTitle(@Param("title") String title);
}
