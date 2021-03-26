package ru.lightcrm.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Уникальный идентификатор проекта.", example = "1", required = true, position = 1)
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "Наименование проекта.", example = "Ревизия складских остатков.", required = true, position = 2)
    private String name;

    @Column(name = "description")
    @ApiModelProperty(notes = "Описание проекта.", example = "Плановая ежегодная ревизия складских остатков.", required = true, position = 3)
    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ApiModelProperty(notes = "Руководитель проекта.", required = true, position = 4)
    private Profile manager;

    @ManyToMany
    @JoinTable(name = "employees_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    @ApiModelProperty(notes = "Список сотрудников имеющих доступ к проекту.", required = false, position = 5)
    private List<Profile> profiles;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;


    public Project(Long id, String name, String description, Profile manager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manager = manager;
    }
}
