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
import ru.lightcrm.entities.dtos.ProjectDTO;
import ru.lightcrm.repositories.ProjectRepository;
import ru.lightcrm.services.interfaces.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@ActiveProfiles("test")
public class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    private static final String PROJECT_NAME = "Проект";
    private static final String PROJECT_DESCRIPTION = "Описание проекта";
    private static final int PROJECT_COUNT = 2;


    private Optional<Project> initMockProject(Long id) {
        Project project = new Project();
        project.setId(id);
        project.setName(PROJECT_NAME + " " + id);
        project.setDescription(PROJECT_DESCRIPTION + " " + id);

        Profile profile = new Profile();
        profile.setId(1L);

        project.setManager(profile);

        return Optional.of(project);
    }

    private List<Project> initMockProjectList(){
        List<Project> projectList = new ArrayList<>();
        for(int i = 0; i < PROJECT_COUNT; i++) projectList.add(initMockProject((long)(i + 1)).orElse(null));

        return projectList;
    }

    @Test
    public void findAll() {
        Mockito.doReturn(initMockProjectList()).when(projectRepository).findAll();

        List<ProjectDTO> projectDTOList = projectService.findAll();
        Assertions.assertNotNull(projectDTOList);
        Assertions.assertEquals(PROJECT_COUNT, projectDTOList.size());
        Assertions.assertEquals(PROJECT_NAME + " 1", projectDTOList.get(0).getName());
        Assertions.assertEquals(1, projectDTOList.get(PROJECT_COUNT - 1).getManagerId());
    }

    @Test
    public void findById() {
        Long id = 1L;
        Mockito.doReturn(initMockProject(id)).when(projectRepository).findById(id);

        ProjectDTO projectDTO = projectService.findById(id);
        Assertions.assertNotNull(projectDTO);
        Assertions.assertEquals(PROJECT_NAME + " " + id, projectDTO.getName());
        Assertions.assertEquals(PROJECT_DESCRIPTION + " " + id, projectDTO.getDescription());
        Assertions.assertEquals(1L, projectDTO.getManagerId());
    }

    @Test
    public void findOneByName() {
        Long id = 1L;
        final String name = PROJECT_NAME + " " + id;
        Mockito.doReturn(initMockProject(id)).when(projectRepository).findOneByName(name);

        ProjectDTO projectDTO = projectService.findOneByName(name);
        Assertions.assertNotNull(projectDTO);
        Assertions.assertEquals(id, projectDTO.getId());
        Assertions.assertEquals(PROJECT_DESCRIPTION + " " + id, projectDTO.getDescription());
        Assertions.assertEquals(1, projectDTO.getManagerId());
    }

    @Test
    public void findByManagerId() {
        Long id = 1L;
        Mockito.doReturn(initMockProjectList()).when(projectRepository).findByManagerId(id);

        List<ProjectDTO> projectDTOList = projectService.findByManagerId(id);
        Assertions.assertNotNull(projectDTOList);
        Assertions.assertEquals(PROJECT_COUNT, projectDTOList.size());
        for(int i = 0; i < PROJECT_COUNT; i++)
            Assertions.assertEquals(id, projectDTOList.get(i).getManagerId());
    }
}
