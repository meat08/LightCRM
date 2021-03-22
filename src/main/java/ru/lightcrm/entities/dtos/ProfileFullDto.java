package ru.lightcrm.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.Profile;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(description = "Класс, представляющий профиль с подробными сведениями о конкретном сотруднике", parent = ProfileDto.class)
public class ProfileFullDto extends ProfileDto {

    @ApiModelProperty(notes = "Пол сотрудника", dataType = "String", example = "М", position = 11)
    private String sex;

    @ApiModelProperty(notes = "Номер телефона сотрудника", dataType = "String", example = "89998887766", position = 12)
    private String phone;

    @Email(message = "Некорректный формат электронной почты сотрудника")
    @ApiModelProperty(notes = "Электронная почта сотрудника", dataType = "String", example = "ivan@mail.com", position = 13)
    private String email;

    @Past(message = "Дата рождения сотрудника должна быть раньше настоящего времени")
    @ApiModelProperty(notes = "Дата рождения сотрудника", dataType = "LocalDate", example = "1990-12-25", position = 14)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @FutureOrPresent(message = "Дата найма должна быть не позже настоящего времени")
    @ApiModelProperty(notes = "Дата найма сотрудника", dataType = "LocalDate", example = "2000-12-25", position = 15)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate employmentDate;

    @FutureOrPresent(message = "Дата увольнения должна быть не позже настоящего времени")
    @ApiModelProperty(notes = "Дата увольнения сотрудника", dataType = "LocalDate", example = "2000-12-25", position = 16)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dismissalDate;

    // Company
    @ApiModelProperty(notes = "Компании, курируемые сотрудником", dataType = "List<CompanyDto>", position = 17)
    private List<CompanyDto> companies;

    // Department
    @Min(1)
    @ApiModelProperty(notes = "Уникальный идентификатор отдела, возглавляемого сотрудником", dataType = "Long", example = "1", position = 18)
    private Long managedDepartmentId;

    @ApiModelProperty(notes = "Уникальный идентификатор отдела, возглавляемого сотрудником", dataType = "String", position = 19)
    private String managedDepartmentName;

    // Comment
    @ApiModelProperty(notes = "Комментарии, оставленные сотрудником", dataType = "List<String>", position = 20)
    private List<CommentDto> comments;

    public ProfileFullDto(Profile profile) {
        super(profile);
        this.sex = profile.getSex();
        this.phone = profile.getPhone();
        this.email = profile.getEmail();
        this.birthdate = profile.getBirthdate();
        this.employmentDate = profile.getEmploymentDate();
        this.dismissalDate = profile.getDismissalDate();
        // Company
        this.companies = profile.getCompanies() != null
                ? profile.getCompanies().stream().map(CompanyDto::new).collect(Collectors.toList())
                : null;
        // Department
        this.managedDepartmentId = profile.getManagedDepartment().getId();
        this.managedDepartmentName = profile.getManagedDepartment().getName();
        // Comment
        this.comments = profile.getComments() != null
                ? profile.getComments().stream().map(CommentDto::new).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
