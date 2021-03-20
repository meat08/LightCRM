package ru.lightcrm.controllers.interfaces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.lightcrm.entities.dtos.ProjectDTO;
import ru.lightcrm.services.interfaces.ProjectService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProjectService projectService;

    private static List<ProjectDTO> testListProjectDto;
    private static ProjectDTO testProjectDto;

    @BeforeAll
    public static void init(){
        testProjectDto = new ProjectDTO();
        testProjectDto.setId(1l);
        testProjectDto.setName("Project test");
        testProjectDto.setDescription("Description of project");
        testProjectDto.setManagerId(1l);

        testListProjectDto = List.of(testProjectDto);

    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getAllProjectsTest() throws Exception{
        given(projectService.findAll()).willReturn(testListProjectDto);

        mvc.perform(get("/api/v1/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(testProjectDto.getName())))
                .andExpect(jsonPath("$[0].description", is(testProjectDto.getDescription())))
                .andExpect(jsonPath("$[0].managerId", is(testProjectDto.getManagerId().intValue())));

    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getProjectByIdTest() throws Exception{
        given(projectService.findById(1L)).willReturn(testProjectDto);

        mvc.perform(get("/api/v1/projects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(testProjectDto.getName())))
                .andExpect(jsonPath("$.description", is(testProjectDto.getDescription())))
                .andExpect(jsonPath("$.managerId", is(testProjectDto.getManagerId().intValue())));
    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getProjectOneByNameTest() throws Exception{
        given(projectService.findOneByName("Проект тестирования")).willReturn(testProjectDto);

        mvc.perform(get("/api/v1/projects/by_name?name=Проект тестирования")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(testProjectDto.getName())))
                .andExpect(jsonPath("$.description", is(testProjectDto.getDescription())))
                .andExpect(jsonPath("$.managerId", is(testProjectDto.getManagerId().intValue())));
    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getProjectsByManagerIdTest() throws Exception{
        given(projectService.findByManagerId(1l)).willReturn(testListProjectDto);

        mvc.perform(get("/api/v1/projects/by_manager?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(testProjectDto.getName())))
                .andExpect(jsonPath("$[0].description", is(testProjectDto.getDescription())))
                .andExpect(jsonPath("$[0].managerId", is(testProjectDto.getManagerId().intValue())));
    }

    //TODO
    //добавить тесты на изменение

}
