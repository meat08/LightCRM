package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Company;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Класс Компания DTO для организации иерархической структуры компании.")
public class CompanyDTO {

    @ApiModelProperty(notes = "Уникальный идентификатор компании.", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "Наименование компании.", example = "Рога и копыта.", required = true, position = 1)
    private String name;

    @ApiModelProperty(notes = "Тип компании.", required = true, position = 2)
    private Boolean type;

    @ApiModelProperty(notes = "ИНН/КПП.", required = true, position = 3)
    private Long inn;

    @ApiModelProperty(notes = "Номер счета", required = true, position = 4)
    private Long billNumber;

    @ApiModelProperty(notes = "Телефон", required = true, position = 5)
    private String phoneNumber;

    @ApiModelProperty(notes = "Email", required = true, position = 6)
    private String email;


    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.type = company.getType();
        this.inn = company.getInn();
        this.billNumber = company.getBillNumber();
        this.phoneNumber = company.getPhoneNumber();
        this.email = company.getEmail();
    }
}
