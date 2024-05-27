package com.example.demo.controller;

import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProjectService;
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
@ActiveProfiles
@AutoConfigureMockMvc
@Transactional
class ProjectControllerTest {
    @Mock
    private ProjectService projectService;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

    private Long testProjectId;

    @BeforeEach
    void setUp() throws Exception {
        MemberAddDto admin = new MemberAddDto("admin","admin","admin","0");
        memberService.addUser(admin);

        ProjectAddDto testProjectAdd=ProjectAddDto.builder()
                .title("test")
                .description("test")
                .PL("1")
                .developer("2")
                .tester("3")
                .build();
        MvcResult mvcResult = this.mvc.perform(post("/addProject")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isCreated())
                .andReturn();
        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        testProjectId=node.get("id").asLong();
    }
/*
    @Test
    @DisplayName("Add Success")
    void addProject() {
        ProjectAddDto projectAddDto = ProjectAddDto.builder()
                .title("test")
                .description("test")
                .build();
        this.mvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectAddDto)))
                .andExpect(status().isCreated());
    }
*/
    @Test
    @DisplayName("get project Success")
    void getProjectList() throws Exception {
        this.mvc.perform(get("/projects/"+testProjectId)
                .param("name","admin")
                .param("password","admin"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllProjects() {
    }

    @AfterEach
    void tearDown() {
    }

}