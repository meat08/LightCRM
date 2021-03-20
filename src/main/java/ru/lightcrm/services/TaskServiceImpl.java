package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.dtos.TaskDTO;
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
    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {
        return new TaskDTO(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задача с id = %s не найден", id))));
    }

    @Override
    public TaskDTO findOneByTitle(String title) {
        return new TaskDTO(taskRepository.findOneByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Задача с наименованием = \"%s\" не найдена", title))));
    }

    @Override
    public List<TaskDTO> findByProducerId(Long id) {
        return taskRepository.findByProducerId(id).stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByProducerIdAndTaskStateId(Long producerId, Long taskStateId) {
        return taskRepository.findByProducerIdAndTaskStateId(producerId, taskStateId).stream()
                            .map(TaskDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByResponsibleId(Long id) {
        return taskRepository.findByResponsibleId(id).stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByResponsibleIdAndTaskStateId(Long responsibleId, Long taskStateId) {
        return taskRepository.findByResponsibleIdAndTaskStateId(responsibleId, taskStateId).stream()
                            .map(TaskDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findByProjectId(Long id) {
        return taskRepository.findByProjectId(id).stream().map(TaskDTO::new).collect(Collectors.toList());
    }
}
