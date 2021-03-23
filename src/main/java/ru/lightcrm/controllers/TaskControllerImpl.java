package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.TaskController;
import ru.lightcrm.entities.dtos.TaskDto;
import ru.lightcrm.services.interfaces.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;

    @Override
    public List<TaskDto> getAllTasks() {
        return taskService.findAll();
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return taskService.findById(id);
    }

    @Override
    public TaskDto saveTask(TaskDto taskDTO) {
        //ожидаем реализацию
        //return taskService.save(taskDTO);
        return null;
    }

    @Override
    public TaskDto updateTask(TaskDto taskDTO) {
        //ожидаем реализацию
        //return taskService.save(taskDTO);
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
        //ожидаем реализацию
        //taskService.deleteById(id)
    }
}
