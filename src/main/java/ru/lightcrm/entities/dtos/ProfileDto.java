package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Department;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.Role;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@ApiModel(description = "Класс, представляющий профиль с основными сведениями о конкретном сотруднике", subTypes = ProfileFullDto.class)
public class ProfileDto {

    @Min(1)
    @ApiModelProperty(notes = "Уникальный идентификатор профиля", dataType = "Long", example = "1", required = true)
    private Long id;

    @Size(min = 3, max = 50, message = "Имя сотрудника должно содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Имя сотрудника", dataType = "String", example = "Иван", required = true, position = 1)
    private String firstname;

    @Size(min = 3, max = 50, message = "Фамилия сотрудника должна содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Фамилия сотрудника", dataType = "String", example = "Иванов", required = true, position = 2)
    private String lastname;

    @Size(min = 3, max = 50, message = "Отчество сотрудника должно содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Отчество сотрудника", dataType = "String", example = "Иванович", required = true, position = 3)
    private String middlename;

    // User
    @Min(1)
    @ApiModelProperty(notes = "Уникальный идентификатор данных авторизации сотрудника", dataType = "Long", example = "1", required = true, position = 4)
    private Long userId;

    @Size(min = 3, max = 50, message = "Логин сотрудника должен содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Логин сотрудника.", dataType = "String", example = "Aladdin", position = 5)
    private String userLogin;

    @ApiModelProperty(notes = "Список прав сотрудника", dataType = "List<String>", required = true, position = 6)
    private Set<String> priorities;

    // StaffUnit
    @Min(1)
    @ApiModelProperty(notes = "Уникальный идентификатор должности, занимаемой сотрудником", dataType = "Long", example = "1", required = true, position = 7)
    private Long staffUnitId;

    @Size(min = 3, max = 50, message = "Название должности сотрудника должно содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Название должности сотрудника", dataType = "String",  required = true,position = 8)
    private String staffUnitName;

    @ApiModelProperty(notes = "Список ролей сотрудника", dataType = "List<String>", required = true, position = 9)
    private Set<String> roles;

    // Department
    @ApiModelProperty(notes = "Список отделов, к которым приписан сотрудник", dataType = "List<String>", required = true, position = 10)
    private List<String> departmentNames;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.middlename = profile.getMiddlename();
        // User
        this.userId = profile.getUser().getId();
        this.userLogin = profile.getUser().getLogin();
        this.priorities = profile.getUser().getPriorities() != null
                ? profile.getUser().getPriorities().stream().map(Priority::getName).collect(Collectors.toSet())
                : Collections.emptySet();
        // StaffUnit
        this.staffUnitId = profile.getStaffUnit().getId();
        this.staffUnitName = profile.getStaffUnit().getName();
        this.roles = profile.getStaffUnit() != null
                ? profile.getStaffUnit().getRoles().stream().map(Role::getName).collect(Collectors.toSet())
                : Collections.emptySet();
        // Department
        this.departmentNames = profile.getDepartments() != null
                ? profile.getDepartments().stream().map(Department::getName).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
