package ru.lightcrm.entities.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Department;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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
    private LocalDate birthday;
    private LocalDate employentDate;
    private LocalDate dismissalDate;
    // User
    private Long userId;
    private String userLogin;
    private Set<String> priorities;
    // StaffUnit
    private Long staffUnitId;
    private String staffUnitName;
    private Set<String> roles;
    // Company
    private List<CompanyDto> companies;
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
        this.birthday = profile.getBirthday();
        this.employentDate = profile.getEmployentDate();
        this.dismissalDate = profile.getDismissalDate();
        // User
        this.userId = profile.getUser().getId();
        this.userLogin = profile.getUser().getLogin();
        this.priorities = profile.getUser().getPriorities().stream().map(Priority::getName).collect(Collectors.toSet());
        // StaffUnit
        this.staffUnitId = profile.getStaffUnit().getId();
        this.staffUnitName = profile.getStaffUnit().getName();
        this.roles = profile.getStaffUnit().getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        // Company
        this.companies = profile.getCompanies() != null
                ? profile.getCompanies().stream().map(CompanyDto::new).collect(Collectors.toList())
                : null;
        // Department
        this.managedDepartmentId = profile.getManagedDepartment().getId();
        this.managedDepartmentName = profile.getManagedDepartment().getName();
        this.departmentNames = profile.getDepartments().stream().map(Department::getName).collect(Collectors.toList());

        // TODO Сущность Comment
//        this.comments = profile.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
