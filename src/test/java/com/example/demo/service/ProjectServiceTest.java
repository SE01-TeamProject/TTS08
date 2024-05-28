package com.example.demo.service;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import com.example.demo.domain.Member;
import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectAddDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;


public class ProjectServiceTest {


    @Mock
    private ProjectRepository projectRepository;


    @Mock
    private MemberRepository memberRepository;


    @InjectMocks
    private ProjectService projectService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddProject_Success() {
        ProjectAddDto projectAddDto = new ProjectAddDto("Title", "Description", "PL", "Developer", "Tester");


        when(projectRepository.findByTitle(any(String.class))).thenReturn(null);
        when(memberRepository.findByName("PL")).thenReturn(new Member("PL1", "PL1", "PL1", 1));
        when(memberRepository.findByName("Developer")).thenReturn(new Member("DEV1", "DEV1", "DEV1", 2));
        when(memberRepository.findByName("Tester")).thenReturn(new Member("TESTER1", "TESTER1", "TESTER1", 3));


        String result = projectService.addProject(projectAddDto);
        assertThat(result).isEqualTo("true");
    }


    @Test
    public void testGetProject_Success() {
        Project project = new Project("Title", "Description", 1, 2, 3);
        when(projectRepository.findById(any(Integer.class))).thenReturn(Optional.of(project));


        String result = projectService.getProject(1);
        assertThat(result).contains("Title");
    }


    @Test
    public void testGetAllProjects_Success() {
        Project project = new Project("Title", "Description", 1, 2, 3);
        when(projectRepository.findAll()).thenReturn(List.of(project));


        List<ProjectDto> result = projectService.getAllProjects();
        assertThat(result).isNotEmpty();
    }
}
