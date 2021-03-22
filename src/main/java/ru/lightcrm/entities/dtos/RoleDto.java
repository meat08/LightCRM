package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.Role;
import java.util.Set;


@ApiModel(description = "Роль DTO")
@Data
public class RoleDto {

    @ApiModelProperty(notes = "Идентификатор роли", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Наименование роли", example = "Менеджер", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Права, принадлежащие данной роли", example = "(Изменение задач, Удаление задач)", required = true, position = 3)
    private Set<Priority> priorities;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.priorities = role.getPriorities();
    }
}
