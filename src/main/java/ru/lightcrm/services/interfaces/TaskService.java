package ru.lightcrm.services.interfaces;

import org.springframework.data.jpa.domain.Specification;
import ru.lightcrm.entities.Task;
import ru.lightcrm.entities.dtos.TaskDto;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskDto> findAll(Map<String, String> params, @Null List<Long> taskStatesId);

    TaskDto findById(Long id);

    Task findEntityById(Long id);

    TaskDto findOneByTitle(String title);

    List<TaskDto> findByProducerId(Long id);

    List<TaskDto> findByProducerIdAndTaskStateId(Long producerId, Long taskStateId);

    List<TaskDto> findByResponsibleId(Long id);

    List<TaskDto> findByResponsibleIdAndTaskStateId(Long responsibleId, Long taskStateId);

    List<TaskDto> findByProjectId(Long id);

    TaskDto saveOrUpdate(TaskDto taskDto);

    void deleteById(Long id);
}
