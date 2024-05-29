package com.example.demo.controller;

import com.example.demo.domain.Issue;
import com.example.demo.domain.Member;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.IssueSetDto;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.IssueService;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.implementation.ToStringMethod;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.expression.Mvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles
class IssueControllerTest {
    @Mock
    private IssueService issueService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    private Integer testIssueId;
    private String testMemberName;
    private Integer testProjectId;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() throws Exception{
        MemberAddDto admin = new MemberAddDto("admin","admin","admin","0");
        memberService.addUser(admin);
        MemberAddDto pl = new MemberAddDto("pl","pl","pl","1");
        memberService.addUser(pl);
        MemberAddDto dev = new MemberAddDto("dev","dev","dev","2");
        memberService.addUser(dev);
        MemberAddDto tester = new MemberAddDto("tester","tester","tester","3");
        memberService.addUser(tester);
        testMemberName=tester.getName();
        MemberAddDto pl2 = new MemberAddDto("pl2","pl2","pl2","1");
        memberService.addUser(pl2);
        MemberAddDto dev2 = new MemberAddDto("dev2","dev2","dev2","2");
        memberService.addUser(dev2);
        MemberAddDto tester2 = new MemberAddDto("tester2","tester2","tester2","3");
        memberService.addUser(tester2);

        ProjectAddDto testProjectAdd=ProjectAddDto.builder()
                .title("test")
                .description("test")
                .PL("pl")
                .developer("dev")
                .tester("tester")
                .build();
        this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isOk());
        testProjectId=projectRepository.findByTitle(testProjectAdd.getTitle()).getId();

//        IssueAddDto issueAddDto = IssueAddDto.builder()
//                .title("test")
//                .description("test")
//                .build();
//        this.mvc.perform(post("/addIssue")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(issueAddDto)))
//                .andExpect(status().isOk());
        IssueAddDto issueAddDto = IssueAddDto.builder()
                .project(Integer.toString(testProjectId))
                .title("test2")
                .description("test2")
                .reporter("tester")
                .priority("Major")
                .type("New")
                .build();
        this.mvc.perform(post("/addIssue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk());
        testIssueId=issueRepository.findByTitle(issueAddDto.getTitle()).getId();
//        IssueSetDto issueSetDto = IssueSetDto.builder()
//                .id(0)
//                .priority(0)
//                .status(0)
//                .build();
//        this.mvc.perform(post("/setIssue")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(issueSetDto)))
//                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("addIssue Success")
    void addIssue()throws Exception {
        IssueAddDto issueAddDto=IssueAddDto.builder()
                .project("0")
                .title("test")
                .description("test")
                .reporter("tester")
                .priority("Major")
                .type("New")
                .build();
        this.mvc.perform(post("/addIssue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("getIssueList Success")
    void getIssueList()throws Exception {
        this.mvc.perform(get("/listIssue"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getIssueList by project id Success")
    void getIssueListByProjectId()throws Exception {
        this.mvc.perform(get("/listIssue/"+testProjectId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getIssue by Issue Id Success")
    void getIssue()throws Exception {
        this.mvc.perform(get("/issue/"+testIssueId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("setAssignee Success")
    void setAssignee()throws Exception {
        IssueSetDto testIssueSet=IssueSetDto.builder()
                .id(testIssueId)
                .priority(issueRepository.findById(testIssueId).orElseThrow().getPriority())
                .status(issueRepository.findById(testIssueId).orElseThrow().getStatus())
                .assignee(testMemberName)
                .build();
        this.mvc.perform(post("/setAssignee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testIssueSet)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("setstate Success")
    void setState() throws Exception{
        IssueSetDto issueSetDto=IssueSetDto.builder()
                .id(testIssueId)
                .priority(Issue.getPriorityFromString("Major"))
                .status(Issue.getStatusFromString("Assigned"))
                .assignee(testMemberName)
                .build();
        this.mvc.perform(post("/setIssue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(issueSetDto)))
                .andExpect(status().isOk());
    }
}