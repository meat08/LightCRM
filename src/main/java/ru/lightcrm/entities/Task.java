package ru.lightcrm.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Задача - основная единица бизнес-процесса.")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Уникальный идентификатор задачи.", example = "1", required = true, position = 0)
    private Long id;

    @Column(name = "title")
    @ApiModelProperty(notes = "Наименование задачи.", example = "Отпуск сотрудника.", required = true, position = 1)
    private String title;

    @Column(name = "description")
    @ApiModelProperty(notes = "Описание задачи.", example = "Соответствующим службам выполнить документальное оформление отпуска.", required = true, position = 2)
    private String description;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    @ApiModelProperty(notes = "Постановщик задачи.", required = true, position = 3)
    private Profile producer;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    @ApiModelProperty(notes = "Ответственный за выполнение (исполнитель) задачи.", required = true, position = 4)
    private Profile responsible;

    @CreationTimestamp
    @Column(name = "start_date")
    @ApiModelProperty(notes = "Дата постановки задачи.", required = true, position = 5)
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    @ApiModelProperty(notes = "Дата окончания задачи.", required = true, position = 6)
    private OffsetDateTime endDate;

    @Column(name = "deadline")
    @ApiModelProperty(notes = "Дата планируемого окончания задачи.", required = true, position = 7)
    private OffsetDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "task_state_id")
    @ApiModelProperty(notes = "Статус состояния задачи.", required = true, position = 8)
    private TaskState taskState;

    @Column(name = "allow_change_deadline")
    @ApiModelProperty(notes = "Признак доступа ответственного к смене даты планируемого окончания задачи.", required = true, position = 10)
    private boolean allowChangeDeadline;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @ApiModelProperty(notes = "Проект к которому относится задача.", required = true, position = 11)
    private Project project;

    @Column(name = "expired")
    @ApiModelProperty(notes = "Просрочена ли задача", required = true, position = 12)
    private boolean expired;

    @ManyToMany
    @JoinTable(name = "tasks_coexecutors",
                joinColumns = @JoinColumn(name = "task_id"),
                inverseJoinColumns = @JoinColumn(name = "profile_id"))
    @ApiModelProperty(notes = "Список соисполнителей задачи", required = true, position = 13)
    private Set<Profile> coExecutors;

    @ManyToMany
    @JoinTable(name = "tasks_spectators",
                joinColumns = @JoinColumn(name = "task_id"),
                inverseJoinColumns = @JoinColumn(name = "profile_id"))
    @ApiModelProperty(notes = "Список наблюдателей задачи", required = true, position = 14)
    private Set<Profile> spectators;

    @OneToMany
    @JoinTable(name = "tasks_comments",
                joinColumns = @JoinColumn(name = "task_id"),
                inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @ApiModelProperty(notes = "Список комментариев к заданию", required = true, position = 15)
    private Set<Comment> comments;
}
