package ru.lightcrm.entities.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String middlename;
    private String sex;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private LocalDate employmentDate;
    private LocalDate dismissalDate;
    // User
    private Long userId;
    private String userLogin;
    private List<String> priorities;
    // StaffUnit
    private Long staffUnitId;
    private String staffUnitName;
    private List<String> roles;
    // Companies
    private List<CompanyDTO> companies;
    // Department
    private Long managedDepartmentId;
    private String managedDepartmentName;
    private List<String> departmentNames;

    // TODO Сущность Comment
//    private List<CommentDto> comments;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.middlename = profile.getMiddlename();
        this.sex = profile.getSex();
        this.phone = profile.getPhone();
        this.email = profile.getEmail();
        this.birthdate = profile.getBirthdate();
        this.employmentDate = profile.getEmploymentDate();
        this.dismissalDate = profile.getDismissalDate();
        // User
        this.userId = profile.getUser().getId();
        this.userLogin = profile.getUser().getLogin();
        this.priorities = profile.getUser().getPriorities().stream().map(Priority::getName).collect(Collectors.toList());
        // StaffUnit
        this.staffUnitId = profile.getStaffUnit().getId();
        this.staffUnitName = profile.getStaffUnit().getName();
        this.roles = profile.getStaffUnit().getRoles().stream().map(Role::getName).collect(Collectors.toList());
        // Companies
        this.companies = profile.getCompanies().stream().map(CompanyDTO::new).collect(Collectors.toList());
        // Department
        this.managedDepartmentId = profile.getManagedDepartment().getId();
        this.managedDepartmentName = profile.getManagedDepartment().getName();
        this.departmentNames = profile.getDepartments().stream().map(Department::getName).collect(Collectors.toList());

        // TODO Сущность Comment
//        this.comments = profile.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
