package ru.lightcrm.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.controllers.interfaces.ProjectController;
import ru.lightcrm.entities.dtos.ProjectDTO;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/projects")
@RestController
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {

    //TODO
    //получить Сервис проектов
    //private ProjectServiceImpl projectService;

    //TODO
    //добавить метод с возвратом страниц Page

    @ApiOperation(value = "Получить список проектов с отбором по параметрам",
            response = ProjectDTO.class,
            responseContainer = "List"
        )
    @GetMapping
    @Override
    public List<ProjectDTO> getProjects(@ApiParam(value = "Map строк с названием параметра и его значением", required = false) @RequestParam Map<String, String> params) {
        //TODO
        //ProjectFilter projectFilter = new ProjectFilter(params);
        //List<Product> projects =  projectService.findAll(projectFilter.getSpec());
        //return projects;
        return null;
    }

    @ApiOperation(value = "Получить проект по его Id",
            response = ProjectDTO.class
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    @Override
    public ProjectDTO getProjectById(@ApiParam(value = "идентификатор проекта", required = true) Long id) {
        //TODO
        //return projectService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find project with id: " + id));
        return null;
    }

    @ApiOperation(value = "Сохранить новый проект",
            response = ProjectDTO.class
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    @Override
    public ProjectDTO saveProject(@ApiParam(value = "новый проект", required = true) ProjectDTO projectDTO) {
        //TODO
        //return projectService.save(projectDTO);
        return null;
    }

    @ApiOperation(value = "Обновить проект",
            response = ProjectDTO.class
    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    @Override
    public ProjectDTO updateProject(@ApiParam(value = "измененный проект", required = true) ProjectDTO projectDTO) {
        //TODO
        //return projectService.save(projectDTO)
        return null;
    }

    @ApiOperation(value = "Удалить проект")
    @DeleteMapping(value = "/{id}")
    @Override
    public void deleteProjectById(@ApiParam(value = "идентификатор удаляемого проекта", required = true) Long id) {
        //TODO
        //projectService.deleteById(id)
    }

    //TODO
    //добавить метод возврата задач проекта

}
