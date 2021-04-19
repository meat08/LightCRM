package ru.lightcrm.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import ru.lightcrm.annotations.SearchableField;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "companies")
public class Company extends SearchableEntity {

    @SearchableField
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private boolean type;

    @Column(name = "inn")
    private Long inn;

    @Column(name = "bill_number")
    private Long billNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "company")
    private Set<Contact> contacts;

    @ManyToMany
    @JoinTable(name = "companies_managers",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<Profile> managers;

    @ManyToMany
    @JoinTable(name = "companies_comments",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments;
}
