package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.UserAssignDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserAssignRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserAssignService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private Integer testAssignId;
    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp()throws Exception {
        MemberAddDto testUser = new MemberAddDto("testUser","testUser","testUser","Tester");
        memberService.addUser(testUser);
        testUserName=testUser.getName();

        MemberAddDto pl = new MemberAddDto("pl","pl","pl","PL");
        memberService.addUser(pl);
        MemberAddDto dev1 = new MemberAddDto("dev1","dev1","dev1","Developer");
        memberService.addUser(dev1);
        MemberAddDto dev2 = new MemberAddDto("dev2","dev2","dev2","Developer");
        memberService.addUser(dev2);
        MemberAddDto dev3 = new MemberAddDto("dev3","dev3","dev3","Developer");
        memberService.addUser(dev3);
        MemberAddDto qa1 = new MemberAddDto("qa1","qa1","qa1","Tester");
        memberService.addUser(qa1);
        MemberAddDto qa2 = new MemberAddDto("qa2","qa2","qa2","Tester");
        memberService.addUser(qa2);
        MemberAddDto qa3 = new MemberAddDto("qa3","qa3","qa3","Tester");
        memberService.addUser(qa3);

        ProjectAddDto testProject = new ProjectAddDto("test","test","pl","dev1","dev2","dev3","qa1","qa2","qa3");
        projectService.addProject(testProject);
        testProjectTitle=testProject.getTitle();
        testProject = new ProjectAddDto("test2","test","pl","dev1","dev2","dev3","qa1","qa2","qa3");
        projectService.addProject(testProject);
        testProject = new ProjectAddDto("test3","test","pl","dev1","dev2","dev3","qa1","qa2","qa3");
        projectService.addProject(testProject);

        UserAssignDto testAssign = UserAssignDto.builder().projectTitle("test2").username("pl").build();
        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("assign user to project Success")
    void assignUserToProject() throws Exception {
        UserAssignDto testAssign = UserAssignDto.builder()
                .projectTitle(testProjectTitle)
                .username(testUserName)
                .build();
        MvcResult mvcResult =this.mockMvc.perform(post("/addassign")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals("true", response);
        System.out.println("Http response : "+response);

    }

    @Test
    @DisplayName("getAssignment Success")
    void getAssignment() throws Exception {
        Integer testAssignId = userAssignRepository.findByUidAndPid(memberRepository.findByName("pl").getId(),projectRepository.findByTitle("test2").getId()).get().getId();
        MvcResult mvcResult= this.mockMvc.perform(get("/assign/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project").value("test2"))
                .andExpect(jsonPath("$.user").value("pl"))
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+response);
    }

    @Test
    @DisplayName("get assignments by pid Success")
    void getAssignmentsByPid()throws Exception {
        UserAssignDto testAssign = UserAssignDto.builder().projectTitle(testProjectTitle).username(testUserName).build();
        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk());
        testAssign = UserAssignDto.builder().projectTitle(testProjectTitle).username("pl").build();
        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk());
        testAssign = UserAssignDto.builder().projectTitle(testProjectTitle).username("dev1").build();
        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk());

        MvcResult mvcResult=this.mockMvc.perform(get("/assign/project/"+projectRepository.findByTitle(testProjectTitle).getId()))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+response);

    }

    @Test
    @DisplayName("get assignments by uid Success")
    void getAssignmentsByUid() throws Exception {
        UserAssignDto testAssign = UserAssignDto.builder().projectTitle(testProjectTitle).username(testUserName).build();
        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk());
        testAssign = UserAssignDto.builder().projectTitle("test2").username(testUserName).build();
        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk());
        testAssign = UserAssignDto.builder().projectTitle("test3").username(testUserName).build();
        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
                .andExpect(status().isOk());

        MvcResult mvcResult=this.mockMvc.perform(get("/assign/user/"+memberRepository.findByName(testUserName).getId()))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+response);
    }

    @Test
    @DisplayName("delete assign Success")
    void deleteAssignmentById() throws Exception {
//        UserAssignDto testAssign = UserAssignDto.builder().projectTitle(testProjectTitle).username(testUserName).build();
//        this.mockMvc.perform((RequestBuilder) post("/addassign").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testAssign)))
//                .andExpect(status().isOk());
        Integer testAssignId = userAssignRepository.findByUidAndPid(memberRepository.findByName("pl").getId(),projectRepository.findByTitle("test2").getId()).get().getId();

        MvcResult mvcResult=this.mockMvc.perform(delete("/delete/"+testAssignId))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("true", response);
        System.out.println("Http response : "+response);
    }
}