package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Department;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Отдел DTO для организации иерархической структуры компании.")
public class DepartmentDTO {

    @ApiModelProperty(notes = "Уникальный идентификатор отдела.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Наименование отдела.", example = "Отдел Информационных Технологий.", required = true, position = 1)
    private String name;

    @ApiModelProperty(notes = "Описание деятельности отдела.", example = "Техническая поддержка сотрудников компании.", required = true, position = 2)
    private String description;

    @ApiModelProperty(notes = "Идентификатор руководителя отдела.", required = true, position = 3)
    private Long leaderId;


    public DepartmentDTO(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
        this.leaderId = department.getLeader().getId();
    }
}
