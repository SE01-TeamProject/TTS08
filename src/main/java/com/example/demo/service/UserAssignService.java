package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Project;

import com.example.demo.domain.UserAssignProj;
import com.example.demo.dto.UserAssignDto;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserAssignRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
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

    public String getAssignment(Integer id){
        Optional<UserAssignProj> userAssignProj=userAssignRepository.findById(id);
        if(userAssignProj.isEmpty()){return "";}

        JSONObject jsonObject = new JSONObject();
        Optional<Member> user=memberRepository.findById(userAssignProj.get().getUid());
        Optional<Project> project=projectRepository.findById(userAssignProj.get().getPid());
        jsonObject.put("project", project.get().getTitle());
        jsonObject.put("user",user.get().getFullName());
        jsonObject.put("level",memberRepository.findById(userAssignProj.get().getUid()).get().getLevel());
        return jsonObject.toString();
    }

    public String getAssignmentByProject(Integer pid){
        List<UserAssignProj> userAssignProj=userAssignRepository.findAllByPid(pid);
        if(userAssignProj.isEmpty()){return "";}
        JSONArray assign = new JSONArray();
        userAssignRepository.findAllByPid(pid).forEach(item ->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("project", projectRepository.findById(item.getPid()).get().getTitle());
            jsonObject.put("user",memberRepository.findById(item.getUid()).get().getFullName());
            jsonObject.put("level",memberRepository.findById(item.getUid()).get().getLevel());
            assign.put(jsonObject);
        });
        return assign.toString();
    }
    public String getAssignmentByMember(Integer uid){
        List<UserAssignProj> userAssignProj=userAssignRepository.findAllByUid(uid);
        if(userAssignProj.isEmpty()){return "";}
        JSONArray assign = new JSONArray();
        userAssignRepository.findAllByUid(uid).forEach(item ->{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("project", projectRepository.findById(item.getPid()).get().getTitle());
            jsonObject.put("user",memberRepository.findById(item.getUid()).get().getFullName());
            jsonObject.put("level",memberRepository.findById(item.getUid()).get().getLevel());
            assign.put(jsonObject);
        });
        return assign.toString();
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

//    public Integer getId(String username, String projectTitle) {
//        Integer uid=memberRepository.findByName(username).getId();
//        Integer pid=projectRepository.findByTitle(projectTitle).getId();
//        Integer aid=userAssignRepository.findByUidAndPid(uid,pid).get().getId();
//        return aid;
//    }

    public String deleteAssignmentById(Integer id) {
        if(userAssignRepository.findById(id).isEmpty()){return "false";}
        userAssignRepository.deleteById(id);
        return "true";
    }

    private UserAssignDto convertToDto(UserAssignProj userAssignProj, String projectTitle, String username) {
        return UserAssignDto.builder()
                .projectTitle(projectTitle)
                .username(username)
                .build();
    }

}
