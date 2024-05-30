package com.example.demo.controller;

import com.example.demo.dto.CommentAddDto;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.CommentService;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles
@AutoConfigureMockMvc
@Transactional
class CommentControllerTest {

    @Mock
    private CommentService commentService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProjectRepository projectRepository;

    private String testMemberName;
    private Integer testProjectId;
    private Integer testCommentId;
    private String testIssuetitle;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private IssueRepository issueRepository;


    @BeforeEach
    void setUp()throws Exception {
        MemberAddDto admin =MemberAddDto.builder().name("admin").fullName("admin").password("admin").level("0").build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk());
        MemberAddDto pl =MemberAddDto.builder().name("pl").fullName("pl").password("pl").level("PL").build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pl)))
                .andExpect(status().isOk());
        MemberAddDto dev =MemberAddDto.builder().name("dev").fullName("dev").password("dev").level("Developer").build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dev)))
                .andExpect(status().isOk());
        MemberAddDto tester =MemberAddDto.builder().name("tester").fullName("tester").password("tester").level("Tester").build();
        this.mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(tester)))
                .andExpect(status().isOk());
        testMemberName=tester.getName();
        ProjectAddDto testProjectAdd=ProjectAddDto.builder().title("test").description("test").PL("pl").developer("dev").tester("tester").build();
        this.mvc.perform(post("/addProject").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isOk());
        testProjectId=projectRepository.findByTitle(testProjectAdd.getTitle()).getId();

        IssueAddDto testIssueAdd=IssueAddDto.builder()
                .project(Integer.toString(testProjectId))
                .title("test")
                .description("test")
                .reporter(testMemberName)
                .priority("Major")
                .type("Bug")
                .build();
        this.mvc.perform(post("/addIssue").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testIssueAdd)))
                .andExpect(status().isOk());
        testIssuetitle = testIssueAdd.getTitle();

        CommentAddDto testCommentAdd=CommentAddDto.builder()
                .issue(testIssuetitle)
                .writer(testMemberName)
                .note("test")
                .build();
        this.mvc.perform(post("/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCommentAdd)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("addComment Success")
    void addComment() throws Exception {
        CommentAddDto testComment=CommentAddDto.builder()
                .issue(testIssuetitle)
                .writer(testMemberName)
                .note("test2")
                .build();
        MvcResult mvcResult = this.mvc.perform(post("/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testComment)))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("true",response);
        System.out.println("Http response : "+response);
        mvcResult= this.mvc.perform(get("/comment/"+2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.note").value("test2"))
                .andExpect(jsonPath("$.writer").value("tester"))
                .andReturn();
        response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+response);
    }

//    @Test //내용없이 comment가 추가 가능
//    @DisplayName("addComment Fail : empty note")
//    void addCommentFail() throws Exception {
//        CommentAddDto testComment=CommentAddDto.builder()
//                .issue(testIssuetitle)
//                .writer(testMemberName)
//                .note("")
//                .build();
//        MvcResult mvcResult = this.mvc.perform(post("/addComment")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(testComment)))
//                .andExpect(status().isOk())
//                .andReturn();
//        String response=mvcResult.getResponse().getContentAsString();
//        assertEquals("true",response);
//        System.out.println("Http response : "+response);
//        mvcResult= this.mvc.perform(get("/comment/"+2))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.note").value(""))
//                .andExpect(jsonPath("$.writer").value("tester"))
//                .andReturn();
//        response=mvcResult.getResponse().getContentAsString();
//        System.out.println("Http response : "+response);
//    }

//    @Test //comment Id를 받을 방법x / comment id는 1부터 순서대로 채움 -> setup에서 추가한 comment id = 1
//    @DisplayName("getComment by Id Success")
//    void getComment() throws Exception {
//        MvcResult mvcResult= this.mvc.perform(get("/comment/"+1))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.note").value("test"))
//                .andExpect(jsonPath("$.writer").value("tester"))
//                .andReturn();
//        String response=mvcResult.getResponse().getContentAsString();
//        System.out.println("Http response : "+response);
//    }

    @Test
    @DisplayName("getComment list Success")
    void getCommentList() throws Exception {
        CommentAddDto testCommentAdd=CommentAddDto.builder().issue(testIssuetitle).writer(testMemberName).note("test")
                .build();
        this.mvc.perform(post("/addComment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testCommentAdd)))
                .andExpect(status().isOk());
        this.mvc.perform(post("/addComment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testCommentAdd)))
                .andExpect(status().isOk());
        this.mvc.perform(post("/addComment").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testCommentAdd)))
                .andExpect(status().isOk());
        Integer testIssueId = issueRepository.findByTitle(testIssuetitle).getId();
        MvcResult mvcResult = this.mvc.perform(get("/listComment/" + testIssueId))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : " + response);
    }
}