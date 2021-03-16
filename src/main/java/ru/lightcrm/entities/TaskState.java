package ru.lightcrm.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_states")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Статус состояния задачи.")
public class TaskState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Уникальный идентификатор статуса.", example = "1", required = true, position = 0)
    private Long id;

    @Column(name = "name_task_state")
    @ApiModelProperty(notes = "Наименование статуса.", example = "Завершена.", required = true, position = 1)
    private String nameTaskState;
}
