package ru.lightcrm.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lightcrm.entities.Priority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel(description = "Права DTO")
@Data
@JsonRootName("PriorityDto")
public class PriorityDto {

    @ApiModelProperty(notes = "Идентификатор права", example = "1", required = true, position = 1)
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(notes = "Наименование права", example = "Менеджер", required = true, position = 2)
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(notes = "Роли, в которые включено данное право", example = "(Administrator, Manager, User)", required = true, position = 3)
    @JsonProperty("roles")
    private Set<RoleDto> roles;

    public PriorityDto(Priority priority) {
        this.id = priority.getId();
        this.name = priority.getName();
        this.roles = priority.getRoles() != null
                ? priority.getRoles().stream().map(RoleDto::new).collect(Collectors.toSet())
                : Collections.emptySet();
    }
}
