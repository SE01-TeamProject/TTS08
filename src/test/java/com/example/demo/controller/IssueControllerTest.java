package com.example.demo.controller;

import com.example.demo.domain.Issue;
import com.example.demo.domain.Member;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.IssueSetDto;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.IssueService;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        MvcResult mvcResult = this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isOk())
                .andReturn();
        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode chkNode = node.get("id");
        if(chkNode!=null)testProjectId=(Integer) chkNode.asInt();
        else testProjectId=null;

        ProjectAddDto testProjectAdd2=ProjectAddDto.builder()
                .title("test2")
                .description("test2")
                .PL("pl2")
                .developer("dev2")
                .tester("tester2")
                .build();
        mvcResult = this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isOk())
                .andReturn();
        JsonNode node2 = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode chkNode2 = node2.get("id");
        if(chkNode2!=null)testIssueId=(Integer)chkNode2.asInt();
        else testIssueId=0;

//        IssueAddDto issueAddDto = IssueAddDto.builder()
//                .title("test")
//                .description("test")
//                .build();
//        this.mvc.perform(post("/addIssue")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(issueAddDto)))
//                .andExpect(status().isOk());
        IssueAddDto issueAddDto = IssueAddDto.builder()
                .title("test2")
                .description("test2")
                .reporter("tester")
                .priority("0")
                .type("0")
                .build();
        this.mvc.perform(post("/addIssue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk());

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
                .title("test")
                .description("test")
                .reporter("tester")
                .priority("0")
                .type("0")
                .build();
        this.mvc.perform(post("/addIssue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk());

    }

//    @Test
//    @DisplayName("getIssueList Success")
//    void getIssueList()throws Exception {
//        this.mvc.perform(get("/listIssue"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("getIssueList by project id Success")
//    void getIssueListByProjectId()throws Exception {
//        this.mvc.perform(get("/listIssue/"+testProjectId))
//                .andExpect(status().isUnauthorized());
//    }

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