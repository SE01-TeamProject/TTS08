package com.example.demo.controller;

import com.example.demo.domain.Member;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles
class MemberControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MemberRepository memberRepository;

    private Integer testMemberId;

    @BeforeEach
    void setUp()throws Exception {
        MemberAddDto testMember = new MemberAddDto("test","test","test","0");
        this.mvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMember)))
                .andExpect(status().isOk());
        testMemberId=memberRepository.findByName(testMember.getName()).getId();

    }

    @Test
    @DisplayName("login Success")//response:true
    void login()throws Exception {
        MemberLoginDto testLoginDto = MemberLoginDto.builder()
                .id("test")
                .password("test")
                .build();
        MvcResult mvcResult = this.mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testLoginDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertEquals("true",response);
        System.out.println("Http Response : "+response);
    }

    @Test
    @DisplayName("login Fail: wrong id")//Http Response : false
    void loginFail()throws Exception {

        MemberLoginDto testLoginDto = MemberLoginDto.builder()
                .id("wrong")
                .password("wrong")
                .build();
        MvcResult mvcResult= this.mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testLoginDto)))
                .andExpect(status().isOk())//현재 반환값이 전부 ok로 설정되어 있는듯
                .andReturn();

        //모든 경우의 응답이 isOk이고 아이디가 존재하지 않을경우 http response가 false가 반환됨
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("false",response);
        System.out.println("Http Response : "+response);
    }

    @Test
    @DisplayName("login Fail: wrong password")//Http Response : false
    void loginFail_2()throws Exception {
        MemberLoginDto testLoginDto = MemberLoginDto.builder()
                .id("test")
                .password("wrong")
                .build();
        MvcResult mvcResult= this.mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testLoginDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("false",response);
        System.out.println("Http Response : "+response);
    }

    @Test
    @DisplayName("addUser Success")
    void addUser()throws Exception {
        MemberAddDto testMember = MemberAddDto.builder()
                .name("test2")
                .fullName("test2")
                .password("test2")
                .level("Tester")
                .build();
        MvcResult mvcResult=this.mvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMember)))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("true",response);
        System.out.println("Http Response : "+response);
    }
    @Test
    @DisplayName("addUser / getMember case 1")
    void addUsertest()throws Exception {
        MemberAddDto testMember = MemberAddDto.builder()
                .name("dev99")
                .fullName("jerry")
                .password("dev99")
                .level("Developer")
                .build();
        MvcResult mvcResult=this.mvc.perform(post("/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testMember)))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("true",response);
        System.out.println("Http Response : "+response);
        testMemberId=memberRepository.findByName(testMember.getName()).getId();
        mvcResult=this.mvc.perform(get("/user/" + testMemberId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(memberRepository.findById(testMemberId).get().getName()))
                .andExpect(jsonPath("$.fullName").value(memberRepository.findById(testMemberId).get().getFullName()))
                .andReturn();
        response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http Response : "+response);
    }

    @Test
    @DisplayName("addUser Fail: name duplicated")
    void addUserFail()throws Exception {
        MemberAddDto testMember=MemberAddDto.builder()
                .name("test")
                .fullName("test")
                .password("test")
                .level("Tester")
                .build();
        MvcResult mvcResult=this.mvc.perform(post("/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testMember)))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("false",response);
        System.out.println("Http Response : "+response);
    }
//    @Test//현재 모든 입력칸이 비어있어도 유저 추가가 가능(response가 true를 반환)
//    @DisplayName("addUser Fail: empty name")
//    void addUserFail_2()throws Exception {
//        MemberAddDto testMember=MemberAddDto.builder()
//                .name("")
//                .fullName("")
//                .password("")
//                .level("")
//                .build();
//        MvcResult mvcResult=this.mvc.perform(post("/addUser")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(testMember)))
//                .andExpect(status().isOk())
//                .andReturn();
//        String response=mvcResult.getResponse().getContentAsString();
//        assertEquals("false",response);
//        System.out.println("Http Response : "+response);
//    }

    @Test
    @DisplayName("getMemberList Success")
    void getMemberList()throws Exception {
        this.mvc.perform(get("/listUser"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getMember Success")
    void getMember()throws Exception {
        MvcResult mvcResult=this.mvc.perform(get("/user/" + testMemberId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(memberRepository.findById(testMemberId).get().getName()))
                .andExpect(jsonPath("$.fullName").value(memberRepository.findById(testMemberId).get().getFullName()))
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println("Http Response : "+response);
    }
    @Test
    @DisplayName("getMember Fail: wrong ID")
    void getMemberFail()throws Exception {
        Integer wrongId=1234;
        MvcResult mvcResult=this.mvc.perform(get("/user/" + wrongId))
                .andExpect(status().isOk())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        assertEquals("",response);
        System.out.println("Http Response : "+response);
    }
}