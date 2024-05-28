package com.example.demo.controller;

import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProjectService;
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
        ProjectAddDto testProjectAdd2=ProjectAddDto.builder()
                .title("test2")
                .description("test2")
                .PL("pl2")
                .developer("dev2")
                .tester("tester2")
                .build();
        MvcResult mvcResult = this.mvc.perform(post("/addProject")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(testProjectAdd)))
                .andExpect(status().isOk())
                .andReturn();
//        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
//        if(node!=null){testProjectId=node.get("id").asLong();}

    }

    @Test
    @DisplayName("Add Success")
    void addProject() throws Exception {
        ProjectAddDto projectAddDto = ProjectAddDto.builder()
                .title("test")
                .description("test")
                .build();
        this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectAddDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getProjectList Success")
    void getProjectList() throws Exception {
        this.mvc.perform(get("/listProject"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getAllProjects")
    void getAllProjects() throws Exception {
        this.mvc.perform(get("/project"))
                .andExpect(status().isOk());
    }

    @AfterEach
    void tearDown() {
    }

}