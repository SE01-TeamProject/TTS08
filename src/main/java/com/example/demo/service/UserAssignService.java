package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.domain.UserAssignProj;
import com.example.demo.dto.UserAssignDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserAssignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserAssignService {

    private final UserAssignRepository userAssignRepository;
    private final ProjectService projectService;
    private final MemberService memberService;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public UserAssignService(UserAssignRepository userAssignRepository, ProjectService projectService, MemberService memberService, ProjectRepository projectRepository, MemberRepository memberRepository) {
        this.userAssignRepository = userAssignRepository;
        this.projectService = projectService;
        this.memberService = memberService;
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
    }

    public String assignUserToProject(UserAssignDto userAssignDto) {
        String projectTitle=userAssignDto.getProjectTitle();
        String username=userAssignDto.getUsername();
        Project project = projectRepository.findByTitle(projectTitle);
        Integer pid;
        Integer uid;
        int levelInt;
        if (project == null) {
            return "false";
        } else {
            pid = project.getId();
        }
        Member member = memberRepository.findByName(username);

        if (member == null) {
            return "false";
        } else {
            uid = member.getId();
            levelInt = member.getLevel();
        }
        UserAssignProj userAssignProj = UserAssignProj.createUserAssignProj(pid, uid, levelInt);
        UserAssignProj savedUserAssignProj = userAssignRepository.save(userAssignProj);
        return "true";
    }

    public Optional<UserAssignDto> findAssignmentById(Integer id) {
        return userAssignRepository.findUserAssignProjById(id)
                .map(userAssignProj -> convertToDto(userAssignProj,
                        projectService.findById(userAssignProj.getPid()).getTitle(),
                        memberService.findById(userAssignProj.getUid()).getName()));
    }

    public List<UserAssignDto> findAssignmentsByPid(Integer pid) {
        String projectTitle = projectService.findById(pid).getTitle();
        return userAssignRepository.findAllByPid(pid).stream()
                .map(userAssignProj -> convertToDto(userAssignProj, projectTitle, memberService.findById(userAssignProj.getUid()).getName()))
                .collect(Collectors.toList());
    }

    public List<UserAssignDto> findAssignmentsByUid(Integer uid) {
        String username = memberService.findById(uid).getName();
        return userAssignRepository.findAllByUid(uid).stream()
                .map(userAssignProj -> convertToDto(userAssignProj, projectService.findById(userAssignProj.getPid()).getTitle(), username))
                .collect(Collectors.toList());
    }

    public void deleteAssignmentById(Integer id) {
        userAssignRepository.deleteById(id);
    }

    private UserAssignDto convertToDto(UserAssignProj userAssignProj, String projectTitle, String username) {
        return UserAssignDto.builder()
                .projectTitle(projectTitle)
                .username(username)
                .level(Member.getStringFromLevel(memberRepository.findByName(username).getLevel()))
                .build();
    }
}
