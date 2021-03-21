package ru.lightcrm.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.User;

import java.util.List;
import java.util.Set;

@ApiModel(description = "Пользователь DTO")
@Data
public class UserDto {

    @ApiModelProperty(notes = "Идентификатор пользователя", example = "1", required = true, position = 1)
    private Long id;

    @ApiModelProperty(notes = "Логин пользователя", example = "User", required = true, position = 2)
    private String login;

    @ApiModelProperty(notes = "Пароль пользователя", example = "123", required = true, position = 3)
    private String password;

    @ApiModelProperty(notes = "Активность пользователя", example = "True", required = true, position = 4)
    private boolean enabled;

    @ApiModelProperty(notes = "Права пользователя", example = "(Изменение задач, Удаление задач)", required = true, position = 5)
    private Set<Priority> priorities;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.priorities = user.getPriorities();
    }
}
