package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Задача.")
public class TaskDto {
    @ApiModelProperty(notes = "Уникальный идентификатор задачи.", example = "1", required = true)
    private Long id;

    @ApiModelProperty(notes = "Наименование задачи.", example = "Отпуск сотрудника.", required = true, position = 1)
    private String title;

    @ApiModelProperty(notes = "Описание задачи.", example = "Соответствующим службам выполнить документальное оформление отпуска.", required = true, position = 2)
    private String description;

    @ApiModelProperty(notes = "Постановщик задачи.", required = true, position = 3)
    private Long producerId;

    @ApiModelProperty(notes = "Ответственный за выполнение (исполнитель) задачи.", required = true, position = 4)
    private Long responsibleId;

    @ApiModelProperty(notes = "Дата постановки задачи.", required = true, position = 5)
    private LocalDateTime startDate;

    @ApiModelProperty(notes = "Дата окончания задачи.", required = true, position = 6)
    private LocalDateTime endDate;

    @ApiModelProperty(notes = "Дата планируемого окончания задачи.", required = true, position = 7)
    private LocalDateTime deadline;

    @ApiModelProperty(notes = "Статус состояния задачи.", required = true, position = 8)
    private Long taskStateId;

    @ApiModelProperty(notes = "Признак доступа ответственного к смене даты планируемого окончания задачи.", required = true, position = 9)
    private boolean allowChangeDeadline;

    @ApiModelProperty(notes = "Проект к которому относится задача.", required = true, position = 10)
    private Long projectId;

    @ApiModelProperty(notes = "Просрочена ли задача", required = true, position = 11)
    private boolean expired;

    @ApiModelProperty(notes = "Список соисполнителей задачи", required = true, position = 12)
    private Set<ProfileDto> coExecutors;

    @ApiModelProperty(notes = "Список наблюдателей задачи", required = true, position = 13)
    private Set<ProfileDto> spectators;

    @ApiModelProperty(notes = "Список комментариев к заданию", required = true, position = 14)
    private Set<CommentDto> comments;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.producerId = task.getProducer().getId();
        this.responsibleId = task.getResponsible().getId();
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
        this.deadline = task.getDeadline();
        this.taskStateId = task.getTaskState().getId();
        this.allowChangeDeadline = task.isAllowChangeDeadline();
        this.projectId = task.getProject().getId();
        this.expired = task.isExpired();
        this.coExecutors = task.getCoExecutors() != null
                ? task.getCoExecutors().stream().map(ProfileDto::new).collect(Collectors.toSet())
                : null;
        this.spectators = task.getSpectators() != null
                ? task.getSpectators().stream().map(ProfileDto::new).collect(Collectors.toSet())
                : null;
        this.comments = task.getComments() != null
                ? task.getComments().stream().map(CommentDto::new).collect(Collectors.toSet())
                : null;
    }
}
