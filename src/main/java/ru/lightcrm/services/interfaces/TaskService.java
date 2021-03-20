package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> findAll();

    TaskDTO findById(Long id);

    TaskDTO findOneByTitle(String title);

    List<TaskDTO> findByProducerId(Long id);

    List<TaskDTO> findByProducerIdAndTaskStateId(Long producerId, Long taskStateId);

    List<TaskDTO> findByResponsibleId(Long id);

    List<TaskDTO> findByResponsibleIdAndTaskStateId(Long responsibleId, Long taskStateId);

    List<TaskDTO> findByProjectId(Long id);
}
