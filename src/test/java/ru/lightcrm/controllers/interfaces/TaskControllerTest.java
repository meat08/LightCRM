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
import ru.lightcrm.entities.dtos.TaskDTO;
import ru.lightcrm.services.interfaces.TaskService;


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
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    private static List<TaskDTO> testListTaskDto;
    private static TaskDTO testTaskDto;

    @BeforeAll
    public static void init(){
        testTaskDto = new TaskDTO();
        testTaskDto.setId(1l);
        testTaskDto.setTitle("Task test");
        testTaskDto.setDescription("Description of task");
        testTaskDto.setProducerId(1l);
        testTaskDto.setResponsibleId(1l);
        testTaskDto.setProjectId(1l);

        testListTaskDto = List.of(testTaskDto);

    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getAllTasksTest() throws Exception{
        given(taskService.findAll()).willReturn(testListTaskDto);

        mvc.perform(get("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(testTaskDto.getTitle())))
                .andExpect(jsonPath("$[0].description", is(testTaskDto.getDescription())))
                .andExpect(jsonPath("$[0].producerId", is(testTaskDto.getProducerId().intValue())))
                .andExpect(jsonPath("$[0].responsibleId", is(testTaskDto.getResponsibleId().intValue())))
                .andExpect(jsonPath("$[0].projectId", is(testTaskDto.getProjectId().intValue())))
                ;

    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getTaskByIdTest() throws Exception{
        given(taskService.findById(1L)).willReturn(testTaskDto);

        mvc.perform(get("/api/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(testTaskDto.getTitle())))
                .andExpect(jsonPath("$.description", is(testTaskDto.getDescription())))
                .andExpect(jsonPath("$.producerId", is(testTaskDto.getProducerId().intValue())))
                .andExpect(jsonPath("$.responsibleId", is(testTaskDto.getResponsibleId().intValue())))
                .andExpect(jsonPath("$.projectId", is(testTaskDto.getProjectId().intValue())))
        ;
    }

}
