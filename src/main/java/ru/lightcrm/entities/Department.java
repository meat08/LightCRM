package ru.lightcrm.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Отдел для организации иерархической структуры компании.")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Уникальный идентификатор отдела.", example = "1", required = true, position = 0)
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "Наименование отдела.", example = "Отдел Информационных Технологий.", required = true, position = 1)
    private String name;

    @Column(name = "description")
    @ApiModelProperty(notes = "Описание деятельности отдела.", example = "Техническая поддержка сотрудников компании.", required = true, position = 2)
    private String description;

    // TODO ожидание сущности Профиль
//    @OneToOne
//    @JoinColumn(name = "leader_id")
//    @ApiModelProperty(notes = "Руководитель отдела.", required = true, position = 3)
//    private Profile leader;
//
//    @ManyToMany
//    @JoinTable(name = "profiles_projects",
//            joinColumns = @JoinColumn(name = "department_id"),
//            inverseJoinColumns = @JoinColumn(name = "profile_id"))
//    @ApiModelProperty(notes = "Список сотрудников работающих в отделе.", required = true, position = 4)
//    private List<Profile> employees;
}
