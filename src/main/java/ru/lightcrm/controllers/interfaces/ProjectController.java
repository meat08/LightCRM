package ru.lightcrm.controllers.interfaces;

import ru.lightcrm.entities.dtos.ProjectDTO;

import java.util.List;
import java.util.Map;

public interface ProjectController {

    //TODO
    //добавить пагинацию
    //PageDTO<ProjectDTO> getProjects(Integer page, Map<String, String> params)

    List<ProjectDTO> getProjects(Map<String, String> params);

    ProjectDTO getProjectById(Long id);

    ProjectDTO saveProject(ProjectDTO projectDTO);

    ProjectDTO updateProject(ProjectDTO projectDTO);

    void deleteProjectById(Long id);

    //TODO
    //добавить метод возврата задач проекта
    //List<TaskDTO> getTasksOfProject(Long id);

}
