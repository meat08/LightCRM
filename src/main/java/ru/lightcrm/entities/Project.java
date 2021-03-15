package ru.lightcrm.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Проект для группировки задач одного бизнес-процесса или направления.")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Уникальный идентификатор проекта.", example = "1", required = true, position = 0)
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "Наименование проекта.", example = "Ревизия складских остатков.", required = true, position = 1)
    private String name;

    @Column(name = "description")
    @ApiModelProperty(notes = "Описание проекта.", example = "Плановая ежегодная ревизия складских остатков.", required = true, position = 2)
    private String description;

    // TODO ожидание сущности Профиль
//    @ManyToOne
//    @JoinColumn(name = "manager_id")
//    @ApiModelProperty(notes = "Руководитель проекта.", required = true, position = 3)
//    private Profile manager;
//
//    @ManyToMany
//    @JoinTable(name = "employees_projects",
//            joinColumns = @JoinColumn(name = "project_id"),
//            inverseJoinColumns = @JoinColumn(name = "profile_id"))
//    @ApiModelProperty(notes = "Список сотрудников имеющих доступ к проекту.", required = false, position = 4)
//    private List<Profile> profiles;
//
//
//    public Project(Long id, String name, String description, Profile manager) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.manager = manager;
//    }
}
