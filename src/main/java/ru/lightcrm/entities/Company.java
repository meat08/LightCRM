package ru.lightcrm.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

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
    private Boolean type;

    @Column(name = "inn")
    private Long inn;

    @Column(name = "billNumber")
    private Long billNumber;

    @OneToMany(mappedBy = "company")
    private List<Contact> contacts;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    //TODO ожидается появление нужных сущностей
//    @OneToMany(mappedBy = "companies")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private List<Comment> comments;
//
//    @OneToMany(mappedBy = "companies")
//    private List<Profile> manager;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ApiModelProperty(notes = "Компания, курируемая сотрудником.", position = 1)
    private Profile manager;

}
