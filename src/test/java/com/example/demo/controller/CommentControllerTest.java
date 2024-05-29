package com.example.demo.controller;

import com.example.demo.dto.CommentAddDto;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.JsonNode;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private String testUserName;
    private Integer testProjectId;
    private Integer testCommentId=0;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MemberRepository memberRepository;


    @BeforeEach
    void setUp()throws Exception {
        MemberAddDto admin = new MemberAddDto("admin","admin","admin","0");
        memberService.addUser(admin);
        MemberAddDto pl = new MemberAddDto("pl","pl","pl","1");
        memberService.addUser(pl);
        MemberAddDto dev = new MemberAddDto("dev","dev","dev","2");
        memberService.addUser(dev);
        MemberAddDto tester = new MemberAddDto("tester","tester","tester","3");
        memberService.addUser(tester);
        testUserName= tester.getName();
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
        CommentAddDto testCommentAdd=CommentAddDto.builder()
                .writer(testUserName)
                .note("test")
                .build();
        this.mvc.perform(post("/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCommentAdd)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("getCommentList Success")
    void getCommentList()throws Exception {
        this.mvc.perform(get("/listComment"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getCommentListbyPID Success")
    void getCommentListbyPID()throws Exception {
        this.mvc.perform(get("/listComment/"+testProjectId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("addComment Success")
    void addComment() throws Exception {
        CommentAddDto testComment=CommentAddDto.builder()
                .writer(testUserName)
                .note("test2")
                .build();
        this.mvc.perform(post("/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testComment)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getComment by Id Success")
    void getComment() throws Exception {
        this.mvc.perform(get("/comment/"+testCommentId))
                .andExpect(status().isOk());
    }
}