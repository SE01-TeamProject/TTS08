package com.example.demo.controller;

import com.example.demo.domain.Issue;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.IssueAddDto;
import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles
class CommentControllerTest {
    @Mock
    private CommentService commentService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

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
        IssueAddDto testIssueAdd= IssueAddDto.builder()
                .title("test")
                .description("test")
                .build();
        this.mvc.perform(post("/addIssue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testIssueAdd)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getCommentList Success")
    void getCommentList() throws Exception{
        this.mvc.perform(get("/listComment"))
                .andExpect(status().isOk());
    }
}