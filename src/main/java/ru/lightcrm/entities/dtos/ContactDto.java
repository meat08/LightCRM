package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.Contact;

@ApiModel(description = "Контактное лицо dto в приложении")
@Data
@NoArgsConstructor
public class ContactDto {
    @ApiModelProperty(notes = "Идентификатор контактного лица", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "ФИО контактного лица", example = "Иван Иванов", required = true, position = 2)
    private String name;

    @ApiModelProperty(notes = "Адрес контактного лица", example = "Москва", required = true, position = 3)
    private String post;

    @ApiModelProperty(notes = "Номер телефона контактного лица", example = "123-45-67-89", required = true, position = 4)
    private String phone;

    @ApiModelProperty(notes = "Электронная почта контактного лица", example = "ivanov@gmail.com", required = true, position = 5)
    private String email;

    @ApiModelProperty(notes = "Дополнительная информация", example = "День рождения 01.12", required = true, position = 6)
    private String description;


    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.post = contact.getPost();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.description = contact.getDescription();

    }
}
