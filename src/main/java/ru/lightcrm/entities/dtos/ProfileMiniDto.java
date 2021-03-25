package ru.lightcrm.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Department;
import ru.lightcrm.entities.Profile;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@ApiModel(description = "Класс, содержащий минимально необходимые данные для создания нового профиля", subTypes = {ProfileDto.class, ProfileFullDto.class, SystemUserDto.class})
public class ProfileMiniDto {

    @Size(min = 3, max = 50, message = "Имя сотрудника должно содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Имя сотрудника", dataType = "String", example = "Иван", required = true, position = 1)
    private String firstname;

    @Size(min = 3, max = 50, message = "Фамилия сотрудника должна содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Фамилия сотрудника", dataType = "String", example = "Иванов", required = true, position = 2)
    private String lastname;

    @Size(min = 3, max = 50, message = "Отчество сотрудника должно содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Отчество сотрудника", dataType = "String", example = "Иванович", required = true, position = 3)
    private String middlename;

    @Size(min = 3, max = 50, message = "Название должности сотрудника должно содержать от 3 до 50 символов")
    @ApiModelProperty(notes = "Название должности сотрудника", dataType = "String", required = true, position = 4)
    private String staffUnitName;

    @PastOrPresent(message = "Дата найма должна быть не позже настоящего времени")
    @ApiModelProperty(notes = "Дата найма сотрудника", dataType = "LocalDate", example = "2000-12-25", position = 5)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate employmentDate;

    @ApiModelProperty(notes = "Список отделов, к которым приписан сотрудник", dataType = "List<String>", required = true, position = 6)
    private List<String> departmentNames;

    public ProfileMiniDto(Profile profile) {
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.middlename = profile.getMiddlename();
        this.employmentDate = profile.getEmploymentDate();
        // StaffUnit
        this.staffUnitName = profile.getStaffUnit() != null
                ? profile.getStaffUnit().getName()
                : null;
        // Department
        this.departmentNames = profile.getDepartments() != null
                ? profile.getDepartments().stream().map(Department::getName).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
