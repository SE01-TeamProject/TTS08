package com.example.demo.repository;

import com.example.demo.domain.UserAssignProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAssignRepository extends JpaRepository<UserAssignProj,Integer> {
    Optional<UserAssignProj> findUserAssignProjById(@Param("id") Integer id);
    Optional<UserAssignProj> findByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);
    List<UserAssignProj> findAllByPid(@Param("pid") Integer pid);
    List<UserAssignProj> findAllByUid(@Param("uid") Integer uid);

}
