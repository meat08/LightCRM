package ru.lightcrm.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private boolean type;

    @Column(name = "inn")
    private Long inn;

    @Column(name = "bill_number")
    private Long billNumber;

    @OneToMany(mappedBy = "company")
    private Set<Contact> contacts;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "companies_managers",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private List<Profile> managers;

    public boolean isType() {
        return type;
    }

    //TODO ожидается появление нужных сущностей
//    private List<Comment> comments;



}
