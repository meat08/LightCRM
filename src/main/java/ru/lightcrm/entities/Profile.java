package ru.lightcrm.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.dtos.SystemUserDto;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "sex")
    private String sex;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private OffsetDateTime birthdate;

    @Column(name = "employment_date")
    private OffsetDateTime employmentDate;

    @Column(name = "dismissal_date")
    private OffsetDateTime dismissalDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "staff_unit_id")
    private StaffUnit staffUnit;

    @ManyToMany
    @JoinTable(name = "companies_managers",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Company> companies;


    @OneToOne(mappedBy = "leader")
    private Department managedDepartment;

    @ManyToMany
    @JoinTable(name = "departments_profiles",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private List<Department> departments;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    public static Profile createNewProfileForUserRegistration(SystemUserDto systemUserDto, User user, StaffUnit staffUnit, List<Department> departments) {
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setStaffUnit(staffUnit);
        profile.setDepartments(departments);
        profile.setFirstname(systemUserDto.getFirstname());
        profile.setLastname(systemUserDto.getLastname());
        profile.setMiddlename(systemUserDto.getMiddlename());
        return profile;
    }
}
