package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.TaskState;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Статус состояния задачи.")
public class TaskStateDTO {
    @ApiModelProperty(notes = "Уникальный идентификатор статуса.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Наименование статуса.", example = "Завершена.", required = true, position = 1)
    private String name;

    public TaskStateDTO(TaskState taskState){
        this.id = taskState.getId();
        this.name = taskState.getName();
    }
}
