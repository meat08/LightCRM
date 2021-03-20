package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.controllers.interfaces.ProjectController;
import ru.lightcrm.entities.dtos.ProjectDTO;
import ru.lightcrm.services.interfaces.ProjectService;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {

    private final ProjectService projectService;

    @Override
    public List<ProjectDTO> getAllProjects(Map<String, String> params) {
        //TODO
        //добавить фильтрацию списка проектов
        //ProjectFilter projectFilter = new ProjectFilter(params);
        //List<ProjectDTO> projects =  projectService.findAll(projectFilter.getSpec());

        List<ProjectDTO> projects =  projectService.findAll();
        return projects;
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        return projectService.findById(id);
    }

    @Override
    public ProjectDTO getProjectOneByName(String name) {
        return projectService.findOneByName(name);
    }

    @Override
    public List<ProjectDTO> getProjectsByManagerId(Long managerId) {
        return projectService.findByManagerId(managerId);
    }

    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        //TODO
        //реализовать сохранение нового
        //return projectService.save(projectDTO);
        return null;

    }

    @Override
    public ProjectDTO updateProject(ProjectDTO projectDTO) {
        //TODO
        //return projectService.save(projectDTO)
        return null;
    }

    @Override
    public void deleteProjectById(Long id) {
        //TODO
        //projectService.deleteById(id)
    }

    //TODO
    //добавить метод возврата задач проекта
    //TODO
    //добавить метод с возвратом страниц Page

}
