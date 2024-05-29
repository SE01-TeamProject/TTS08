package com.example.demo.controller;

import com.example.demo.dto.MemberAddDto;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.repository.ProjectRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Autowired
    private ProjectRepository projectRepository;

    private Integer testProjectId;

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

//        ProjectAddDto testProjectAdd=ProjectAddDto.builder()
//                .title("test")
//                .description("test")
//                .PL("pl")
//                .developer("dev")
//                .tester("tester")
//                .build();
//        this.mvc.perform(post("/addProject")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(testProjectAdd)))
//                .andExpect(status().isOk());

        ProjectAddDto testProjectAdd2=ProjectAddDto.builder()
                .title("test2")
                .description("test2")
                .PL("pl2")
                .developer("dev2")
                .tester("tester2")
                .build();
        this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProjectAdd2)))
                .andExpect(status().isOk());
        testProjectId=projectRepository.findByTitle(testProjectAdd2.getTitle()).getId();

    }

    @Test
    @DisplayName("Add Success")
    void addProject() throws Exception {
        ProjectAddDto projectAddDto = ProjectAddDto.builder()
                .title("test")
                .description("test")
                .PL("pl")
                .developer("dev")
                .tester("tester")
                .build();
        MvcResult mvcResult=this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectAddDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        assertEquals("true", response);
        System.out.println("Http response : "+response);
    }

    @Test
    @DisplayName("Add Fail: title duplicated")
    void addProjectFail() throws Exception {
        ProjectAddDto projectAddDto = ProjectAddDto.builder()
                .title("test2")
                .description("test")
                .PL("pl")
                .developer("dev")
                .tester("tester")
                .build();
        MvcResult mvcResult=this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectAddDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        assertEquals("false", response);
        System.out.println("Http response : "+response);
    }

    @Test //현재 title이 비었을 때 addproject가 동작함
    @DisplayName("Add Fail: empty title")
    void addProjectFail_2() throws Exception {
        ProjectAddDto projectAddDto = ProjectAddDto.builder()
                .title("")
                .description("")
                .PL("pl")
                .developer("dev")
                .tester("tester")
                .build();
        MvcResult mvcResult=this.mvc.perform(post("/addProject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectAddDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+response);
        assertEquals("false", response);
    }

    @Test
    @DisplayName("getProject by id Success")
    void getProject()throws Exception {
        MvcResult mvcResult=this.mvc.perform(get("/project/{id}",testProjectId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(projectRepository.findById(testProjectId).get().getTitle()))
                .andExpect(jsonPath("$.description").value(projectRepository.findById(testProjectId).get().getDescription()))
                .andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        System.out.println("Http response : "+response);
    }

    @Test
    @DisplayName("getProject by id Fail: wrong Id")
    void getProjectFail()throws Exception {
        Integer wrongId=1234;
        MvcResult mvcResult=this.mvc.perform(get("/project/{id}",wrongId))
                .andExpect(status().isOk())
                .andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        assertEquals("", response);
        System.out.println("Http response : "+response);
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