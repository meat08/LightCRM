package ru.lightcrm.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.utils.CustomDateDeserializer;

import javax.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(description = "Класс, представляющий профиль с подробными сведениями о конкретном сотруднике", parent = ProfileDto.class)
public class ProfileFullDto extends ProfileDto {

    @ApiModelProperty(notes = "Пол сотрудника", dataType = "String", example = "М", position = 12)
    private String sex;

    @ApiModelProperty(notes = "Номер телефона сотрудника", dataType = "String", example = "89998887766", position = 13)
    private String phone;

    @Email(message = "Некорректный формат электронной почты сотрудника")
    @ApiModelProperty(notes = "Электронная почта сотрудника", dataType = "String", example = "ivan@mail.com", position = 14)
    private String email;

    @Past(message = "Дата рождения сотрудника должна быть раньше настоящего времени")
    @ApiModelProperty(notes = "Дата рождения сотрудника", dataType = "OffsetDateTime", example = "1990-12-25", position = 15)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private OffsetDateTime birthdate;

    @PastOrPresent(message = "Дата увольнения должна быть не позже настоящего времени")
    @ApiModelProperty(notes = "Дата увольнения сотрудника", dataType = "OffsetDateTime", example = "2000-12-25", position = 16)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private OffsetDateTime dismissalDate;

    // Company
    @ApiModelProperty(notes = "Компании, курируемые сотрудником", dataType = "List<CompanyDto>", position = 17)
    private List<String> companyNames;

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
        this.dismissalDate = profile.getDismissalDate();
        // Company
        //todo мини Dto для компании - companyId + companyName (иначе циклическая ссылка)
        //временно сделан лист названий компаний
        this.companyNames = profile.getCompanies() != null
                ? profile.getCompanies().stream().map(Company::getName).collect(Collectors.toList())
                : new ArrayList<>();
        // Department
        this.managedDepartmentId = profile.getManagedDepartment() != null
                ? profile.getManagedDepartment().getId()
                : null;
        this.managedDepartmentName = profile.getManagedDepartment() != null
                ? profile.getManagedDepartment().getName()
                : null;
        // Comment
        this.comments = profile.getComments() != null
                ? profile.getComments().stream().map(CommentDto::new).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
