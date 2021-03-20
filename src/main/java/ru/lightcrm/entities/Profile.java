package ru.lightcrm.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@ApiModel(description = "Класс, представляющий профиль с подробными сведениями о конкретном сотруднике.")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Уникальный идентификатор профиля.", example = "1", required = true, position = 0)
    private Long id;

    @Column(name = "firstname")
    @ApiModelProperty(notes = "Имя сотрудника.", example = "Иван", required = true, position = 1)
    private String firstname;

    @Column(name = "lastname")
    @ApiModelProperty(notes = "Фамилия сотрудника.", example = "Иванов", required = true, position = 2)
    private String lastname;

    @Column(name = "middlename")
    @ApiModelProperty(notes = "Отчество сотрудника.", example = "Иванович", required = true, position = 3)
    private String middlename;

    @Column(name = "sex")
    @ApiModelProperty(notes = "Пол сотрудника.", example = "М", position = 4)
    private String sex;

    @Column(name = "phone")
    @ApiModelProperty(notes = "Телефонный номер сотрудника.", required = true, position = 5)
    private String phone;

    @Column(name = "email")
    @ApiModelProperty(notes = "Электронная почта сотрудника.", example = "ivan@mail.com", position = 6)
    private String email;

    @Column(name = "birthday")
    @ApiModelProperty(notes = "Дата рождения сотрудника.", example = "1990-12-25", position = 7)
    private LocalDate birthday;

    @Column(name = "employent_date")
    @ApiModelProperty(notes = "Дата найма сотрудника.", example = "2000-12-25", position = 8)
    private LocalDate employentDate;

    @Column(name = "dismissal_date")
    @ApiModelProperty(notes = "Дата увольнения сотрудника.", example = "2000-12-25", position = 9)
    private LocalDate dismissalDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "Данные авторизации сотрудника.", required = true, position = 10)
    private User user;

    @OneToOne
    @JoinColumn(name = "staff_unit_id")
    @ApiModelProperty(notes = "Описание должности сотрудника.", required = true, position = 11)
    private StaffUnit staffUnit;

    @ManyToMany
    @JoinTable(name = "companies_managers",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    @ApiModelProperty(notes = "Компании, курируемые сотрудником.", position = 12)
    private List<Company> companies;


    @OneToOne(mappedBy = "leader")
    @ApiModelProperty(notes = "Отдел, возглавляемый сотрудником.", position = 13)
    private Department managedDepartment;

    @ManyToMany
    @JoinTable(name = "profiles_departments",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    @ApiModelProperty(notes = "Отделы, к которым приписан сотрудник.", required = true, position = 14)
    private List<Department> departments;

    // TODO Сущность Comment
//    @OneToMany(mappedBy = "author")
//    @ApiModelProperty(notes = "Комментарии, оставленные сотрудником.", position = 15)
//    private List<Comment> comments;

}
