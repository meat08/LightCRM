package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Проект DTO для группировки задач одного бизнес-процесса или направления.")
public class ProjectDTO {

    @ApiModelProperty(notes = "Уникальный идентификатор проекта.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Наименование проекта.", example = "Ревизия складских остатков.", required = true, position = 1)
    private String name;

    @ApiModelProperty(notes = "Описание проекта.", example = "Плановая ежегодная ревизия складских остатков.", required = true, position = 2)
    private String description;

    @ApiModelProperty(notes = "Идентификатор руководителя проекта.", required = true, position = 3)
    private Long manager_id;

    // TODO ожидание сущности Профиль
//    public ProjectDTO(Project project) {
//        this.id = project.getId();
//        this.name = project.getName();
//        this.description = project.getDescription();
//        this.manager_id = project.getManager().getID();
//    }
}
