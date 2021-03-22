package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.entities.dtos.ProjectDto;

import java.util.List;

@Api(value = "Работа с проектами")
@RequestMapping("/api/v1/projects")
public interface ProjectController {

    @ApiOperation(value = "Получить список проектов",
            response = ProjectDto.class,
            responseContainer = "List"
    )
    @GetMapping
    List<ProjectDto> getAllProjects();

    @ApiOperation(value = "Получить проект по его Id",
            response = ProjectDto.class
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    ProjectDto getProjectById(@ApiParam(value = "идентификатор проекта", required = true) @PathVariable Long id);

    @ApiOperation(value = "Сохранить новый проект (не работает)",
            response = ProjectDto.class

    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    ProjectDto saveProject(@ApiParam(value = "новый проект", required = true) @RequestBody ProjectDto projectDTO);

    @ApiOperation(value = "Обновить проект (не работает)",
            response = ProjectDto.class

    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    ProjectDto updateProject(@ApiParam(value = "измененный проект", required = true) @RequestBody ProjectDto projectDTO);

    @ApiOperation(value = "Удалить проект (не работает)")
    @DeleteMapping(value = "/{id}")
    void deleteProjectById(@ApiParam(value = "идентификатор удаляемого проекта", required = true) @PathVariable Long id);

}
