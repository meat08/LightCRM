package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.entities.dtos.TaskDto;

import java.util.List;

@Api(value = "Работа с задачами")
@RequestMapping("/api/v1/tasks")
public interface TaskController {

    @ApiOperation(value = "Получить список задач",
            response = TaskDto.class,
            responseContainer = "List"
    )
    @GetMapping
    List<TaskDto> getAllTasks();

    @ApiOperation(value = "Получить задачу по её Id",
            response = TaskDto.class
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    TaskDto getTaskById(@ApiParam(value = "идентификатор задачи", required = true) @PathVariable Long id);

    @ApiOperation(value = "Сохранить новую задачу (не работает)",
            response = TaskDto.class

    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    TaskDto saveTask(@ApiParam(value = "новая задача", required = true) @RequestBody TaskDto taskDTO);

    @ApiOperation(value = "Обновить задачу (не работает)",
            response = TaskDto.class

    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    TaskDto updateTask(@ApiParam(value = "измененная задача", required = true) @RequestBody TaskDto taskDTO);

    @ApiOperation(value = "Удалить задачу (не работает)")
    @DeleteMapping(value = "/{id}")
    void deleteTaskById(@ApiParam(value = "идентификатор удаляемой задачи", required = true) @PathVariable Long id);


}
