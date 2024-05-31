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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAssignService {

    private final UserAssignRepository userAssignRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;


    //프로젝트에 유저를 할당하는 메소드
    public String assignUserToProject(UserAssignDto userAssignDto) {
        String projectTitle=userAssignDto.getProjectTitle();
        String username=userAssignDto.getUsername();
        Project project = projectRepository.findByTitle(projectTitle);
        if (project == null) {return "false";}
        Member member = memberRepository.findByName(username);
        if (member == null) {return "false";}
        UserAssignProj userAssign=userAssignRepository.findByUidAndPid(memberRepository.findByName(username).getId(),projectRepository.findByTitle(projectTitle).getId());
        if(userAssign != null){return "false";}

        Integer pid = project.getId();
        Integer uid = member.getId();
        int levelInt= member.getLevel();
        UserAssignProj userAssignProj = UserAssignProj.createUserAssignProj(pid, uid, levelInt);
        userAssignRepository.save(userAssignProj);
        return "true";
    }

    //userAssignedProject id를 사용해 할당 정보를 가져오는 메소드
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

    //특정 프로젝트에 할당된 정보를 전부 가져오는 메소드
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

    //특정 유저에 할당된 정보를 전부 가져오는 메소드
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

    //할당 정보 삭제 메소드
    public String deleteAssignmentById(Integer id) {
        if(userAssignRepository.findById(id).isEmpty()){return "false";}
        userAssignRepository.deleteById(id);
        return "true";
    }
}
