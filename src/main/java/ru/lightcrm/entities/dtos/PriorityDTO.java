package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.Role;
import java.util.Collection;
import java.util.Set;

@ApiModel(description = "Права DTO")
@Data
public class PriorityDTO {
    @ApiModelProperty(notes = "Идентификатор права", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Наименование права", example = "Менеджер", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Роли, в которые включено данное право", example = "(Administrator, Manager, User)", required = true, position = 3)
    private Set<Role> roles;

    public PriorityDTO(Priority priority) {
        this.id = priority.getId();
        this.name = priority.getName();
        this.roles = priority.getRoles();
    }
}
