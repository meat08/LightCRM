package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.entities.dtos.TaskDTO;

import java.util.List;

@Api(value = "Работа с задачами")
@RequestMapping("/api/v1/tasks")
public interface TaskController {

    @ApiOperation(value = "Получить список задач",
            response = TaskDTO.class,
            responseContainer = "List"
    )
    @GetMapping
    List<TaskDTO> getAllTasks();

    @ApiOperation(value = "Получить задачу по её Id",
            response = TaskDTO.class
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    TaskDTO getTaskById(@ApiParam(value = "идентификатор задачи", required = true) @PathVariable Long id);

    @ApiOperation(value = "Сохранить новую задачу (не работает)",
            response = TaskDTO.class

    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    TaskDTO saveTask(@ApiParam(value = "новая задача", required = true) @RequestBody TaskDTO taskDTO);

    @ApiOperation(value = "Обновить задачу (не работает)",
            response = TaskDTO.class

    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    TaskDTO updateTask(@ApiParam(value = "измененная задача", required = true) @RequestBody TaskDTO taskDTO);

    @ApiOperation(value = "Удалить задачу (не работает)")
    @DeleteMapping(value = "/{id}")
    void deleteTaskById(@ApiParam(value = "идентификатор удаляемой задачи", required = true) @PathVariable Long id);


}
