package ru.lightcrm.controllers.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.lightcrm.entities.dtos.TaskDTO;
import ru.lightcrm.services.interfaces.TaskService;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    private static List<TaskDTO> testListTaskDto;
    private static TaskDTO testTaskDto;

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

}
