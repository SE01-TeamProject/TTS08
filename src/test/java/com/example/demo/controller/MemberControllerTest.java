package com.example.demo.controller;

import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.MemberLoginDto;
import com.example.demo.repository.MemberRepository;
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
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles
class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberRepository memberRepository;

    private Integer testMemberId=0;

    @BeforeEach
    void setUp()throws Exception {
        MemberAddDto testMember = new MemberAddDto("test","test","test","0");
        memberService.addUser(testMember);

    }

    @Test
    @DisplayName("login Success")
    void login()throws Exception {
        MemberLoginDto testLoginDto = MemberLoginDto.builder()
                .id("test")
                .password("test")
                .build();
        this.mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testLoginDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("addUser Success")
    void addUser()throws Exception {
        MemberAddDto testMember = MemberAddDto.builder()
                .name("test2")
                .fullName("test2")
                .password("test2")
                .level("0")
                .build();
        this.mvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMember)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getMemberList Success")
    void getMemberList()throws Exception {
        this.mvc.perform(get("/listUser"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getMember Success")
    void getMember()throws Exception {
        this.mvc.perform(get("/user/" + testMemberId))
                .andExpect(status().isOk());
    }
}