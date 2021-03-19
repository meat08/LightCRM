package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.TaskController;
import ru.lightcrm.entities.dtos.TaskDTO;
import ru.lightcrm.services.interfaces.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

  private final TaskService taskService;

  @GetMapping
  public List<TaskDTO> getTasksContent() {
    return taskService.findAll();
  }
}
