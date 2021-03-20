package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.entities.dtos.ProjectDTO;

import java.util.List;
import java.util.Map;

@Api(value = "Работа с проектами")
@RequestMapping("/api/v1/projects")
public interface ProjectController {

    @ApiOperation(value = "Получить список проектов с отбором по параметрам",
            response = ProjectDTO.class,
            responseContainer = "List"
    )
    @GetMapping
    List<ProjectDTO> getAllProjects(@ApiParam(value = "Map строк с названием параметра и его значением", required = false) @RequestParam Map<String, String> params);

    @ApiOperation(value = "Получить проект по его Id",
            response = ProjectDTO.class
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    ProjectDTO getProjectById(@ApiParam(value = "идентификатор проекта", required = true) @PathVariable Long id);

    //TODO
    //убрать метод, использовать getAllProjects с параметрами
    @ApiOperation(value = "Получить проект по его названию",
            response = ProjectDTO.class
    )
    @GetMapping(value = "/by_name", produces = "application/json")
    ProjectDTO getProjectOneByName(@ApiParam(value = "название проекта", required = true) @RequestParam String name);

    //TODO
    //убрать метод, использовать getAllProjects с параметрами
    @ApiOperation(value = "Получить проекты по Id менеджера проекта",
            response = ProjectDTO.class,
            responseContainer = "List"
    )
    @GetMapping(value = "/by_manager", produces = "application/json")
    List<ProjectDTO> getProjectsByManagerId(@ApiParam(value = "id менеджера", required = true) @RequestParam(name = "id") Long managerId);

    @ApiOperation(value = "Сохранить новый проект",
            response = ProjectDTO.class
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    ProjectDTO saveProject(@ApiParam(value = "новый проект", required = true) @RequestBody ProjectDTO projectDTO);

    @ApiOperation(value = "Обновить проект",
            response = ProjectDTO.class
    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    ProjectDTO updateProject(@ApiParam(value = "измененный проект", required = true) @RequestBody ProjectDTO projectDTO);

    @ApiOperation(value = "Удалить проект")
    @DeleteMapping(value = "/{id}")
    void deleteProjectById(@ApiParam(value = "идентификатор удаляемого проекта", required = true) @PathVariable Long id);

    //TODO
    //добавить метод возврата задач проекта
    //List<TaskDTO> getTasksOfProject(Long id);
    //TODO
    //добавить пагинацию
    //PageDTO<ProjectDTO> getProjects(Integer page, Map<String, String> params)

}
