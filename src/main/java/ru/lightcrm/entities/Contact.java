package ru.lightcrm.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "post")
    private String post;

    @Column(name = "phone")
    @Size(min = 5)
    private String phone;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "description")
    private String description;

}