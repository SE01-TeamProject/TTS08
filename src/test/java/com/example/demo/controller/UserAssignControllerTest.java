package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.UserAssignDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.UserAssignRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserAssignService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@SpringBootTest
@ActiveProfiles
@AutoConfigureMockMvc
@Transactional
class UserAssignControllerTest {

    @Mock
    private UserAssignService userAssignService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProjectService projectService;

    private String testProjectTitle;
    private String testUserName;
    @Autowired
    private UserAssignRepository userAssignRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp()throws Exception {
        MemberAddDto testUser = new MemberAddDto("testUser","testUser","testUser","3");
        memberService.addUser(testUser);

        MemberAddDto pl = new MemberAddDto("pl","pl","pl","1");
        memberService.addUser(testUser);
        MemberAddDto dev1 = new MemberAddDto("dev1","dev1","dev1","2");
        memberService.addUser(testUser);
        MemberAddDto dev2 = new MemberAddDto("dev2","dev2","dev2","2");
        memberService.addUser(testUser);
        MemberAddDto dev3 = new MemberAddDto("dev3","dev3","dev3","2");
        memberService.addUser(testUser);
        MemberAddDto qa1 = new MemberAddDto("qa1","qa1","qa1","3");
        memberService.addUser(testUser);
        MemberAddDto qa2 = new MemberAddDto("qa2","qa2","qa2","3");
        memberService.addUser(testUser);
        MemberAddDto qa3 = new MemberAddDto("qa3","qa3","qa3","3");
        memberService.addUser(testUser);

        testUserName=testUser.getName();
        ProjectAddDto testProject = new ProjectAddDto("test","test","pl","dev1","dev2","dev3","qa1","qa2","qa3");
        projectService.addProject(testProject);
        testProjectTitle=testProject.getTitle();

    }

    @Test
    void assignUserToProject() {
        UserAssignDto testAssign = UserAssignDto.builder()
                .projectTitle(testProjectTitle)
                .username(testUserName)
                .level(Member.getStringFromLevel(memberRepository.findByName(testUserName).getLevel()))
                .build();
        this.mockMvc.perform(post("/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .content())

    }

    @Test
    void getAssignment() {
    }

    @Test
    void getAssignmentsByPid() {
    }

    @Test
    void getAssignmentsByUid() {
    }

    @Test
    void deleteAssignmentById() {
    }
}