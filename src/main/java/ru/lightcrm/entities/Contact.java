package ru.lightcrm.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lightcrm.annotations.SearchableField;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
public class Contact extends SearchableEntity {

    @SearchableField
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}