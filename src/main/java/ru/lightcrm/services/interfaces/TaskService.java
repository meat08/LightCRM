package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.Task;
import ru.lightcrm.entities.dtos.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();

    TaskDto findById(Long id);

    Task findEntityById(Long id);

    TaskDto findOneByTitle(String title);

    List<TaskDto> findByProducerId(Long id);

    List<TaskDto> findByProducerIdAndTaskStateId(Long producerId, Long taskStateId);

    List<TaskDto> findByResponsibleId(Long id);

    List<TaskDto> findByResponsibleIdAndTaskStateId(Long responsibleId, Long taskStateId);

    List<TaskDto> findByProjectId(Long id);
}
