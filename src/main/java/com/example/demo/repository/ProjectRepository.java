package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectDto;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Optional<Project> findById(@Param("id") Integer id);
	Project findByTitle(@Param("title") String title);
}
