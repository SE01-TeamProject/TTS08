package com.example.demo.controller;

import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.IssueSetDto;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
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

    private Long testIssueId;

    private Long testProjectId;

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
        if(chkNode!=null)testProjectId=chkNode.asLong();
        else testProjectId=null;

        ProjectAddDto testProjectAdd2=ProjectAddDto.builder()
                .title("test2")
                .description("test2")
                .PL("pl2")
                .developer("dev2")
                .tester("tester2")
                .build();
        this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isOk());

        IssueAddDto issueAddDto = IssueAddDto.builder()
                .title("test")
                .description("test")
                .build();
        this.mvc.perform(post("/addIssue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk());
        issueAddDto = IssueAddDto.builder()
                .title("test2")
                .description("test2")
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
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("setstate Success")
    void setState() throws Exception{
        IssueSetDto issueSetDto=IssueSetDto.builder()
                .id(0)
                .priority(1)
                .status(1)
                .build();
        this.mvc.perform(post("/setIssue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(issueSetDto)))
                .andExpect(status().isOk());
    }
}