package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	Optional<Member> findById(@Param("id") Integer id);
	Member findByName(@Param("name") String name);
//    Member save(Member member);
//    Optional<Member> findById(Integer id);
//    Optional<Member> findByName(String name);
//    List<Member> findAll();
}
