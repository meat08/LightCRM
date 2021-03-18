package ru.lightcrm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.Project;
import ru.lightcrm.entities.Task;
import ru.lightcrm.entities.TaskState;
import ru.lightcrm.entities.dtos.TaskDTO;
import ru.lightcrm.repositories.TaskRepository;
import ru.lightcrm.services.interfaces.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    private static final String TASK_NAME = "Задача";
    private static final String TASK_DESCRIPTION = "Описание задачи";
    private static final int TASK_COUNT = 2;

    private Optional<Task> initMockTask(Long id) {
        Task task = new Task();
        task.setId(id);
        task.setTitle(TASK_NAME + " " + id);
        task.setDescription(TASK_DESCRIPTION + " " + id);

        Profile producer = new Profile();
        producer.setId(1L);
        task.setProducer(producer);

        Profile responsible = new Profile();
        responsible.setId(2L);
        task.setResponsible(responsible);

        Project project = new Project();
        project.setId(3L);
        task.setProject(project);

        TaskState taskState = new TaskState();
        taskState.setId(1L);
        task.setTaskState(taskState);

        return Optional.of(task);
    }

    private List<Task> initMockTaskList(){
        List<Task> taskList = new ArrayList<>();
        for(int i = 0; i < TASK_COUNT; i++) taskList.add(initMockTask((long)(i + 1)).orElse(null));

        return taskList;
    }

    @Test
    public void findAllTest() {
        Mockito.doReturn(initMockTaskList()).when(taskRepository).findAll();

        List<TaskDTO> taskDTOList = taskService.findAll();
        Assertions.assertNotNull(taskDTOList);
        Assertions.assertEquals(TASK_COUNT, taskDTOList.size());
        Assertions.assertEquals(TASK_NAME + " 1", taskDTOList.get(0).getTitle());
        Assertions.assertEquals(1, taskDTOList.get(TASK_COUNT - 1).getProducerId());
        Assertions.assertEquals(2, taskDTOList.get(TASK_COUNT - 1).getResponsibleId());
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        Mockito.doReturn(initMockTask(id)).when(taskRepository).findById(id);

        TaskDTO taskDTO = taskService.findById(id);
        Assertions.assertNotNull(taskDTO);
        Assertions.assertEquals(TASK_NAME + " " + id, taskDTO.getTitle());
        Assertions.assertEquals(TASK_DESCRIPTION + " " + id, taskDTO.getDescription());
        Assertions.assertEquals(1L, taskDTO.getProducerId());
        Assertions.assertEquals(2L, taskDTO.getResponsibleId());
    }

    @Test
    public void findOneByTitleTest() {
        Long id = 1L;
        final String name = TASK_NAME + " " + id;
        Mockito.doReturn(initMockTask(id)).when(taskRepository).findOneByTitle(name);

        TaskDTO taskDTO = taskService.findOneByTitle(name);
        Assertions.assertNotNull(taskDTO);
        Assertions.assertEquals(id, taskDTO.getId());
        Assertions.assertEquals(TASK_DESCRIPTION + " " + id, taskDTO.getDescription());
        Assertions.assertEquals(1, taskDTO.getTaskStateId());
    }

    @Test
    public void findByProducerIdTest() {
        Long id = 1L;
        Mockito.doReturn(initMockTaskList()).when(taskRepository).findByProducerId(id);

        List<TaskDTO> taskDTOList = taskService.findByProducerId(id);
        Assertions.assertNotNull(taskDTOList);
        Assertions.assertEquals(TASK_COUNT, taskDTOList.size());
        for(int i = 0; i < TASK_COUNT; i++)
            Assertions.assertEquals(id, taskDTOList.get(i).getProducerId());
    }

    @Test
    public void findByProducerIdAndTaskStateIdTest() {
        Long producerId = 1L, taskStateId = 1L;
        Mockito.doReturn(initMockTaskList()).when(taskRepository).findByProducerIdAndTaskStateId(producerId, taskStateId);

        List<TaskDTO> taskDTOList = taskService.findByProducerIdAndTaskStateId(producerId, taskStateId);
        Assertions.assertNotNull(taskDTOList);
        Assertions.assertEquals(TASK_COUNT, taskDTOList.size());
        for(int i = 0; i < TASK_COUNT; i++) {
            Assertions.assertEquals(producerId, taskDTOList.get(i).getProducerId());
            Assertions.assertEquals(taskStateId, taskDTOList.get(i).getTaskStateId());
        }
    }

    @Test
    public void findByResponsibleIdTest() {
        Long id = 2L;
        Mockito.doReturn(initMockTaskList()).when(taskRepository).findByResponsibleId(id);

        List<TaskDTO> taskDTOList = taskService.findByResponsibleId(id);
        Assertions.assertNotNull(taskDTOList);
        Assertions.assertEquals(TASK_COUNT, taskDTOList.size());
        for(int i = 0; i < TASK_COUNT; i++)
            Assertions.assertEquals(id, taskDTOList.get(i).getResponsibleId());
    }

    @Test
    public void findByResponsibleIdAndTaskStateIdTest() {
        Long responsibleId = 2L, taskStateId = 1L;
        Mockito.doReturn(initMockTaskList()).when(taskRepository).findByResponsibleIdAndTaskStateId(responsibleId, taskStateId);

        List<TaskDTO> taskDTOList = taskService.findByResponsibleIdAndTaskStateId(responsibleId, taskStateId);
        Assertions.assertNotNull(taskDTOList);
        Assertions.assertEquals(TASK_COUNT, taskDTOList.size());
        for(int i = 0; i < TASK_COUNT; i++) {
            Assertions.assertEquals(responsibleId, taskDTOList.get(i).getResponsibleId());
            Assertions.assertEquals(taskStateId, taskDTOList.get(i).getTaskStateId());
        }
    }

    @Test
    public void findByProjectIdTest() {
        Long id = 3L;
        Mockito.doReturn(initMockTaskList()).when(taskRepository).findByProjectId(id);

        List<TaskDTO> taskDTOList = taskService.findByProjectId(id);
        Assertions.assertNotNull(taskDTOList);
        Assertions.assertEquals(TASK_COUNT, taskDTOList.size());
        for(int i = 0; i < TASK_COUNT; i++)
            Assertions.assertEquals(id, taskDTOList.get(i).getProjectId());
    }
}
