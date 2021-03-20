package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lightcrm.entities.dtos.TaskDTO;

import java.util.List;

@Api(value = "Работа с задачами")
@RequestMapping("/api/v1/tasks")
public interface TaskController {

    @ApiOperation(value = "Получить список проектов с отбором по параметрам",
            response = TaskDTO.class,
            responseContainer = "List"
    )
    @GetMapping
    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO findOneByTitle(String title);

    List<TaskDTO> findByProducerId(Long id);

    List<TaskDTO> findByProducerIdAndTaskStateId(Long producerId, Long taskStateId);

    List<TaskDTO> findByResponsibleId(Long id);

    List<TaskDTO> findByResponsibleIdAndTaskStateId(Long responsibleId, Long taskStateId);

    List<TaskDTO> findByProjectId(Long id);

}
