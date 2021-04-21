package ru.lightcrm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "task_states")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskState extends BaseEntity {

    @Column(name = "name")
    private String name;
}
