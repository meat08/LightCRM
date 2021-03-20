package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.entities.dtos.ProjectDTO;

import java.util.List;

@Api(value = "Работа с проектами")
@RequestMapping("/api/v1/projects")
public interface ProjectController {

    @ApiOperation(value = "Получить список проектов",
            response = ProjectDTO.class,
            responseContainer = "List"
    )
    @GetMapping
    List<ProjectDTO> getAllProjects();

    @ApiOperation(value = "Получить проект по его Id",
            response = ProjectDTO.class
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    ProjectDTO getProjectById(@ApiParam(value = "идентификатор проекта", required = true) @PathVariable Long id);

    @ApiOperation(value = "Сохранить новый проект (не работает)",
            response = ProjectDTO.class

    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    ProjectDTO saveProject(@ApiParam(value = "новый проект", required = true) @RequestBody ProjectDTO projectDTO);

    @ApiOperation(value = "Обновить проект (не работает)",
            response = ProjectDTO.class

    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    ProjectDTO updateProject(@ApiParam(value = "измененный проект", required = true) @RequestBody ProjectDTO projectDTO);

    @ApiOperation(value = "Удалить проект (не работает)")
    @DeleteMapping(value = "/{id}")
    void deleteProjectById(@ApiParam(value = "идентификатор удаляемого проекта", required = true) @PathVariable Long id);

}
