package ru.lightcrm.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    public boolean isType() {
        return type;
    }

    //TODO ожидается появление нужных сущностей
//    @OneToMany(mappedBy = "companies")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private List<Comment> comments;
//
//    @OneToMany(mappedBy = "companies")
//    private List<Profile> manager;


}
