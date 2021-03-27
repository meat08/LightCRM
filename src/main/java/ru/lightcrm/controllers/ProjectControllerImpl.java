package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.controllers.interfaces.ProjectController;
import ru.lightcrm.entities.dtos.ProjectDto;
import ru.lightcrm.services.interfaces.ProjectService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {

    private final ProjectService projectService;

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectService.findAll();
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        return projectService.findById(id);
    }

    @Override
    public ProjectDto saveProject(ProjectDto projectDTO) {
        projectDTO.setId(null);
        return projectService.saveOrUpdate(projectDTO);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDTO) {
        return projectService.saveOrUpdate(projectDTO);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectService.deleteById(id);
    }

}
