package ru.lightcrm.services.filters;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.lightcrm.entities.Task;
import ru.lightcrm.repositories.specifications.TaskSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class TaskFilter {

    private Specification<Task> spec;

    public TaskFilter(Map<String, String> params, List<Long> taskStatesId) {

        spec = Specification.where(null);

        if(params == null){
            return;
        }

        if (params.containsKey("executorId") && !params.get("executorId").isBlank()) {
            Long executorId = Long.parseLong(params.get("executorId"));
            spec = spec.and(TaskSpecification.executorEquals(executorId));
        }

        if (params.containsKey("responsibleId") && !params.get("responsibleId").isBlank()) {
            Long responsibleId = Long.parseLong(params.get("responsibleId"));
            spec = spec.and(TaskSpecification.responsibleEquals(responsibleId));
        }

        if (params.containsKey("producerId") && !params.get("producerId").isBlank()) {
            Long producerId = Long.parseLong(params.get("producerId"));
            spec = spec.and(TaskSpecification.producerEquals(producerId));
        }

        if (params.containsKey("taskStateId") && !params.get("taskStateId").isBlank()){
            Long taskStateId = Long.parseLong(params.get("taskStateId"));
            spec = spec.and(TaskSpecification.taskStateEquals(taskStateId));
        }

        if (taskStatesId != null && !taskStatesId.isEmpty()){
            spec = spec.and(TaskSpecification.taskStatesEquals(taskStatesId));
        }

        if (params.containsKey("coExecutorId") && !params.get("coExecutorId").isBlank()){
            Long coExecutorId = Long.parseLong(params.get("coExecutorId"));
            spec = spec.and(TaskSpecification.coExecutorsEquals(coExecutorId));
        }

    }
}
