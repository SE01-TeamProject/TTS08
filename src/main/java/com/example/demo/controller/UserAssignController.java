package com.example.demo.controller;

import com.example.demo.dto.UserAssignDto;
import com.example.demo.service.UserAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-assign")
public class UserAssignController {

    private final UserAssignService us;

    @Autowired
    public UserAssignController(UserAssignService us) {this.us = us;}

    @PostMapping("/assign")
    public String assignUserToProject(@RequestBody UserAssignDto userAssignDto) {
        return us.assignUserToProject(userAssignDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAssignDto> getAssignment(@PathVariable Integer id) {
        Optional<UserAssignDto> userAssignDto = us.findAssignmentById(id);
        return userAssignDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/projec/{pid}")
    public ResponseEntity<List<UserAssignDto>> getAssignmentsByPid(@PathVariable Integer pid) {
        List<UserAssignDto> assignments = us.findAssignmentsByPid(pid);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<List<UserAssignDto>> getAssignmentsByUid(@PathVariable Integer uid) {
        List<UserAssignDto> assignments = us.findAssignmentsByUid(uid);
        return ResponseEntity.ok(assignments);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAssignmentById(@PathVariable Integer id) {
        us.deleteAssignmentById(id);
        return ResponseEntity.noContent().build();
    }
}
