package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lightcrm.entities.dtos.TaskDTO;

import java.util.List;

@Api(value = "Задачи")
public interface TaskController {
  @GetMapping("/api/v1/tasks")
  @ApiOperation(value = " Возвращает cписок DTO задач",
      notes = "Запрос списка задач",
      httpMethod = "GET"
  )
  List<TaskDTO> getTasksContent();

}
