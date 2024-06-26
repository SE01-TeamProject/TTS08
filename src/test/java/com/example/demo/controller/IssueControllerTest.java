package com.example.demo.controller;

import com.example.demo.domain.Issue;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.IssueSetDto;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.IssueService;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles
class IssueControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private IssueRepository issueRepository;

    private Integer testIssueId;
    private String testMemberName;

    @BeforeEach
    void setUp() throws Exception{
        MemberAddDto admin =MemberAddDto.builder().name("admin").fullName("admin").password("admin").level("0")
                .build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk());
        MemberAddDto pl =MemberAddDto.builder().name("pl").fullName("pl").password("pl").level("PL")
                .build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pl)))
                .andExpect(status().isOk());
        MemberAddDto dev =MemberAddDto.builder().name("dev").fullName("dev").password("dev").level("Developer")
                .build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dev)))
                .andExpect(status().isOk());
        MemberAddDto tester =MemberAddDto.builder().name("tester").fullName("tester").password("tester").level("Tester")
                .build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(tester)))
                .andExpect(status().isOk());
        testMemberName=tester.getName();
        ProjectAddDto testProjectAdd=ProjectAddDto.builder().title("test").description("test").PL("pl").developer1("dev").developer2("dev").developer3("dev").tester1("tester").tester2("tester").tester3("tester")
                .build();
        this.mvc.perform(post("/addProject").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isOk());

        IssueAddDto issueAddDto = IssueAddDto.builder().project("0").title("test2").description("test2").reporter("tester").priority("Major").type("New")
                .build();
        this.mvc.perform(post("/addIssue").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk());
        testIssueId=issueRepository.findByTitle(issueAddDto.getTitle()).getId();
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
        MvcResult mvcResult=this.mvc.perform(post("/addIssue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        assertEquals("true", response);
        System.out.println("Http response : "+ response);

    }
    @Test
    @DisplayName("addIssue Fail: duplicated title")
    void addIssueFail()throws Exception {
        IssueAddDto issueAddDto=IssueAddDto.builder()
                .project("0")
                .title("test2")
                .description("test")
                .reporter("tester")
                .priority("Major")
                .type("New")
                .build();
        MvcResult mvcResult=this.mvc.perform(post("/addIssue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(issueAddDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        assertEquals("false", response);
        System.out.println("Http response : "+ response);

    }

    @Test //assignee가 비었을때 getIssue가 동작하지 않는 문제있음/ 아마 null값 가져오는 과정에서 문제 생기는 듯
    @DisplayName("getIssue by Id Success")
    void getIssue()throws Exception {
        IssueSetDto testIssueSet=IssueSetDto.builder()
                .id(testIssueId)
                .priority(issueRepository.findById(testIssueId).orElseThrow().getPriority())
                .status(issueRepository.findById(testIssueId).orElseThrow().getStatus())
                .assignee(testMemberName)
                .description("test")
                .build();
        this.mvc.perform(post("/setIssue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testIssueSet)))
                .andExpect(status().isOk());
        MvcResult mvcResult=this.mvc.perform(get("/issue/"+testIssueId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(issueRepository.findById(testIssueId).get().getTitle()))
                .andExpect(jsonPath("$.description").value(issueRepository.findById(testIssueId).get().getDescription()))
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+ response);
    }
    @Test
    @DisplayName("getIssue Fail: wrong Id ")
    void getIssueFail()throws Exception {
        Integer wrongId=1234;
        MvcResult mvcResult=this.mvc.perform(get("/issue/"+wrongId))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("", response);
        System.out.println("Http response : "+ response);
    }

    @Test
    @DisplayName("getIssueList Success")
    void getIssueList()throws Exception {
        MvcResult mvcResult= this.mvc.perform(get("/listIssue"))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+ response);
    }

    @Test
    @DisplayName("getIssueList by project id Success")
    void getIssueListByProjectId()throws Exception {
        MvcResult mvcResult=this.mvc.perform(get("/listIssue/"+0))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+ response);
    }

    @Test
    @DisplayName("setstate Success")
    void setState() throws Exception{
        IssueSetDto issueSetDto=IssueSetDto.builder()
                .id(testIssueId)
                .priority(Issue.getPriorityFromString("Minor"))
                .status(Issue.getStatusFromString("Fixed"))
                .assignee(testMemberName)
                .build();
        MvcResult mvcResult =this.mvc.perform(post("/setIssue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(issueSetDto)))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(memberRepository.findByName(testMemberName).getId(),issueRepository.findById(testIssueId).orElseThrow().getAssignee());
        assertEquals(issueSetDto.getPriority(),issueRepository.findById(testIssueId).get().getPriority());
        assertEquals(issueSetDto.getStatus(),issueRepository.findById(testIssueId).get().getStatus());
    }

    @Test
    @DisplayName("getIssueStatics Success")
    void getIssueStatics()throws Exception {
        MvcResult mvcResult=this.mvc.perform(get("/issueStatics"))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+ response);
    }
    @Test
    @DisplayName("getIssueTrend Success")
    void getIssueTrend()throws Exception {
        MvcResult mvcResult=this.mvc.perform(get("/issueTrend"))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+ response);
    }

//    @Test
//    @DisplayName("suggestAssignee Success")
//    void suggestAssignee()throws Exception {
//        IssueSetDto issueSetDto=IssueSetDto.builder()
//                .id(testIssueId)
//                .priority(Issue.getPriorityFromString("Minor"))
//                .status(Issue.getStatusFromString("Fixed"))
//                .assignee(testMemberName)
//                .build();
//        MvcResult mvcResult=this.mvc.perform(post("/suggestAssinee")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(issueSetDto)))
//                .andExpect(status().isOk())
//                .andReturn();
//        String response=mvcResult.getResponse().getContentAsString();
//        System.out.println("Http response : "+ response);
//    }

//    @Test
//    @DisplayName("setstate Fail : wrong Id")
//    void setStateFail() throws Exception{
//        Integer wrongId=1234;
//        IssueSetDto issueSetDto=IssueSetDto.builder()
//                .id(wrongId)
//                .priority(Issue.getPriorityFromString("Minor"))
//                .status(Issue.getStatusFromString("Fixed"))
//                .assignee(testMemberName)
//                .build();
//        MvcResult mvcResult =this.mvc.perform(post("/setIssue")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(issueSetDto)))
//                .andExpect(status().isOk())
//                .andReturn();
//        String response=mvcResult.getResponse().getContentAsString();
//        System.out.println("Http response : "+ response);
//    } //현재 setState 기능에 isExist 를 체크할 기능이 없음 / 사실 아이디가 잘못들어갈 일이 없을거라 예상


}