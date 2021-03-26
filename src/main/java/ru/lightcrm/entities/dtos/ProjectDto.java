package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Project;

import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Проект DTO для группировки задач одного бизнес-процесса или направления.")
public class ProjectDto {

    @ApiModelProperty(notes = "Уникальный идентификатор проекта.", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Наименование проекта.", example = "Ревизия складских остатков.", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Описание проекта.", example = "Плановая ежегодная ревизия складских остатков.", required = true, position = 3)
    private String description;

    @ApiModelProperty(notes = "Идентификатор руководителя проекта.", required = true, position = 4)
    private ProfileDto manager;

    @ApiModelProperty(notes = "Список ответственных за проект.", required = true, position = 5)
    private Set<ProfileDto> profiles;

    @ApiModelProperty(notes = "Список заданий проекта.", required = true, position = 6)
    private Set<TaskDto> tasks;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.manager = new ProfileDto(project.getManager());
        this.profiles = project.getProfiles() != null
                ? project.getProfiles().stream().map(ProfileDto::new).collect(Collectors.toSet())
                : null;
        this.tasks = null;

//        TODO - добавление Таска не работает, получается петля и StackOverFlow, поэтому пока NULL
//        this.tasks = project.getTasks() != null
//                ? project.getTasks().stream().map(TaskDto::new).collect(Collectors.toSet())
//                : null;
    }
}
