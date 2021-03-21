package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.StaffUnit;
import java.util.Collection;
import java.util.Set;

@ApiModel(description = "Штатная единица DTO")
@Data
public class StaffUnitDto {

    @ApiModelProperty(notes = "Идентификатор штатной единицы", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Наименование штатной единицы", example = "Менеджер", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Роли штатной единицы", example = "(Administrator, Manager, User)", required = true, position = 3)
    private Set<Role> roles;

    public StaffUnitDto(StaffUnit staffUnit) {
        this.id = staffUnit.getId();
        this.name = staffUnit.getName();
        this.roles = staffUnit.getRoles();
    }

}
