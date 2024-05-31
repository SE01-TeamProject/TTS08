package com.example.demo.controller;

import com.example.demo.dto.UserAssignDto;
import com.example.demo.service.UserAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAssignController {

    private final UserAssignService us;

    @Autowired
    public UserAssignController(UserAssignService us) {this.us = us;}

    @PostMapping("/addassign")
    public String assignUserToProject(@RequestBody UserAssignDto userAssignDto) {
        return us.assignUserToProject(userAssignDto);
    }

    @GetMapping("/assign/{id}")
    public String getAssignment(@PathVariable("id") Integer id) {return us.getAssignment(id);}

    @GetMapping("/assign/project/{pid}")
    public String getAssignmentByPid(@PathVariable Integer pid) {
        return us.getAssignmentByProject(pid);
    }

    @GetMapping("/assign/user/{uid}")
    public String getAssignmentByUid(@PathVariable Integer uid) {
        return us.getAssignmentByMember(uid);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAssignmentById(@PathVariable Integer id) {
        return us.deleteAssignmentById(id);
    }
}
