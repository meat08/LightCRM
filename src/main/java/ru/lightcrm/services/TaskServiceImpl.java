package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Task;
import ru.lightcrm.entities.dtos.TaskDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.TaskRepository;
import ru.lightcrm.services.interfaces.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
    }

    @Override
    public Task findEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задача с id = %s не найден", id)));
    }

    @Override
    public TaskDto findById(Long id) {
        return new TaskDto(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задача с id = %s не найден", id))));
    }

    @Override
    public TaskDto findOneByTitle(String title) {
        return new TaskDto(taskRepository.findOneByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задача с наименованием = \"%s\" не найдена", title))));
    }

    @Override
    public List<TaskDto> findByProducerId(Long id) {
        return taskRepository.findByProducerId(id).stream().map(TaskDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByProducerIdAndTaskStateId(Long producerId, Long taskStateId) {
        return taskRepository.findByProducerIdAndTaskStateId(producerId, taskStateId).stream()
                            .map(TaskDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByResponsibleId(Long id) {
        return taskRepository.findByResponsibleId(id).stream().map(TaskDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByResponsibleIdAndTaskStateId(Long responsibleId, Long taskStateId) {
        return taskRepository.findByResponsibleIdAndTaskStateId(responsibleId, taskStateId).stream()
                            .map(TaskDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> findByProjectId(Long id) {
        return taskRepository.findByProjectId(id).stream().map(TaskDto::new).collect(Collectors.toList());
    }
}
