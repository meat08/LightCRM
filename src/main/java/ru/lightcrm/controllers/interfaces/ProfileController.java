package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.*;
import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lightcrm.entities.dtos.ProfileDto;

import java.util.List;

@Api(value = "/api/v1/profiles", tags = "Контроллер для работы с профилями", produces = "application/json")
public interface ProfileController {

    @ApiOperation(value = "Возвращает профиль по указанному id", httpMethod = "GET", produces = "application/json", response = ProfileDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileDto.class),
            @ApiResponse(code = 400, message = "Указан некорректны id"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Профиль с указанным id отсутствует")
    })
    ProfileDto getById(@ApiParam(value = "Уникальный идентификатор профиля", name = "id", required = true, example = "1") Long id);

  @ApiOperation(value = "Возвращает профиль по объекту Principal", httpMethod = "GET", produces = "application/json", response = ProfileDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = ProfileDto.class),
      @ApiResponse(code = 401, message = "Клиент не авторизован"),
      @ApiResponse(code = 403, message = "Нет прав"),
      @ApiResponse(code = 404, message = "Пользователь userId не найден")
  })
    ProfileDto getProfile(Principal principal);

  @ApiOperation(value = "Возвращает список всех профилей", httpMethod = "GET", produces = "application/json", response = ProfileDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileDto.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует")
    })
    List<ProfileDto> getAll();
}
