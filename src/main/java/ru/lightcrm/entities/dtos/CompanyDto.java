package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.Contact;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApiModel(description = "Компания DTO")
@Data
@NoArgsConstructor
public class CompanyDto {

    @ApiModelProperty(notes = "Идентификатор компании", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Наименование компании", example = "Газпром", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Тип компании", example = "True", required = true, position = 3)
    private boolean type;

    @ApiModelProperty(notes = "ИНН компании", example = "1234567890123", required = true, position = 4)
    private Long inn;

    @ApiModelProperty(notes = "Номер счета компании", example = "21321321425434", required = true, position = 5)
    private Long billNumber;

    @ApiModelProperty(notes = "Список контактов компании", example = "(Иванов, Петров)", required = true, position = 6)
    private Set<Contact> contacts;

    @ApiModelProperty(notes = "Номер телефона компании", example = "88009999999", required = true, position = 7)
    private String phoneNumber;

    @ApiModelProperty(notes = "Email компании", example = "GazpromInfo@gazprom.ru", required = true, position = 8)
    private String email;

    @ApiModelProperty(notes = "Курирующие менеджеры компании", example = "(Иванов, Петров)", required = true, position = 9)
    private List<ProfileDto> managers;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.type = company.isType();
        this.inn = company.getInn();
        this.billNumber = company.getBillNumber();
        this.contacts = company.getContacts();
        this.phoneNumber = company.getPhoneNumber();
        this.email = company.getEmail();
        //TODO на обсуждении (карточка 81), может ли у компании не быть курирующего менеджера
        this.managers = company.getManagers() != null
                ? company.getManagers().stream().map(ProfileDto::new).collect(Collectors.toList())
                : null;

        // TODO ожидание сущностей Менеджер и Комментарий
    }
}
