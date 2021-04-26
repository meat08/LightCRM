package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.annotations.SearchableEntityController;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.dtos.*;

import java.security.Principal;
import java.util.List;

@SearchableEntityController(url = "/api/v1/profiles", entityClass = Profile.class)
@Api(value = "/api/v1/profiles", tags = "Контроллер для работы с профилями и рабочими днями сотрудников", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/api/v1/profiles", produces = "application/json")
public interface ProfileController {

    @ApiOperation(value = "Возвращает профиль c основными характеристиками по указанному id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileDto.class),
            @ApiResponse(code = 400, message = "Указан некорректны id"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Профиль с указанным id отсутствует")
    })
    @GetMapping(value = "/{id}")
    ProfileDto getById(@ApiParam(value = "Уникальный идентификатор профиля", name = "id", required = true, example = "1") @PathVariable Long id);

    @ApiOperation(value = "Возвращает список всех профилей с основными характеристиками", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileDto.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует")
    })
    @GetMapping
    List<ProfileDto> getAll();

    @ApiOperation(value = "Возвращает профиль с подробными характеристиками по указанному id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileFullDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileFullDto.class),
            @ApiResponse(code = 400, message = "Указан некорректны id"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Профиль с указанным id отсутствует")
    })
    @GetMapping(value = "/full/{id}")
    ProfileFullDto getProfileFullById(@ApiParam(value = "Уникальный идентификатор профиля", name = "id", required = true, example = "1") @PathVariable Long id);

    @ApiOperation(value = "Возвращает список всех профилей с подробными характеристиками", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileFullDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileFullDto.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует")
    })
    @GetMapping(value = "/full")
    List<ProfileFullDto> getAllProfilesFull();

    @ApiOperation(value = "Возвращает профиль с подробными характеристиками авторизованного пользователя", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileFullDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileFullDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Пользователь userId не найден")
    })
    @GetMapping("/profile")
    ProfileFullDto getProfileFull(Principal principal);

    @ApiOperation(value = "Возвращает профиль с базовыми характеристиками по указанному id", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileMiniDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileMiniDto.class),
            @ApiResponse(code = 400, message = "Указан некорректны id"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Профиль с указанным id отсутствует")
    })
    @GetMapping(value = "/mini/{id}")
    ProfileMiniDto getProfileMiniById(@ApiParam(value = "Уникальный идентификатор профиля", name = "id", required = true, example = "1") @PathVariable Long id);

    @ApiOperation(value = "Возвращает список всех профилей с базовыми характеристиками", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileMiniDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileMiniDto.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует")
    })
    @GetMapping(value = "/mini")
    List<ProfileMiniDto> getAllProfilesMini();

    @ApiOperation(value = "Возвращает профиль с базовыми характеристиками авторизованного пользователя", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ProfileMiniDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProfileMiniDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Пользователь userId не найден")
    })
    @GetMapping("/profile/mini")
    ProfileMiniDto getProfileMini(Principal principal);

    @ApiOperation(value = "Регистрация нового пользователя", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 201, message = "Новый пользователь успешно создан", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Некорректное тело запроса"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует"),
            @ApiResponse(code = 415, message = "Неподдерживаемый тип данных")
    })
    @PostMapping(value = "/register", consumes = "application/json")
    ResponseEntity<?> saveNewUser(
            @ApiParam(value = "JSON представление данных нового пользователя", name = "systemUserDto", required = true,
                    example = "{\"firstname\": \"Иван\", \"lastname\": \"Иванов\", \"middlename\": \"Иванович\", " +
                            "\"staffUnitName\": \"string\", \"employmentDate\": \"2012-10-23\", \"departmentNames\": [\"string\"], " +
                            "\"login\": \"Aladdin\", \"password\": \"12345\", \"confirmationPassword\": \"12345\"}")
            @RequestBody @Validated SystemUserDto systemUserDto, BindingResult bindingResult
    );

    @ApiOperation(value = "Возвращает рабочий день текущего пользователя за указанную дату", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = WorkingDayDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = WorkingDayDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует")
    })
    @GetMapping("/profile/working_day/{date}")
    WorkingDayDto getCurrentUserWorkingDay(Principal principal,
                                           @ApiParam(value = "Дата рабочего дня", name = "date", required = true, example = "2021-04-16") @PathVariable String date);

    @ApiOperation(value = "Возвращает рабочий день указанного пользователя за указанную дату", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = WorkingDayDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = WorkingDayDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует")
    })
    @GetMapping("/{id}/working_day/{date}")
    WorkingDayDto getWorkingDayByProfileId(@ApiParam(value = "Уникальный идентификатор профиля", name = "id", required = true, example = "1") @PathVariable Long id,
                                           @PathVariable String date);

    @ApiOperation(value = "Создает новый рабочий день пользователя с указанным идентификатором", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 201, message = "Новый рабочий день успешно создан", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Некорректное тело запроса"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует"),
            @ApiResponse(code = 415, message = "Неподдерживаемый тип данных")
    })
    @PostMapping("/working_day")
    ResponseEntity<?> createWorkingDayByProfileId(@ApiParam(value = "JSON представление рабочего дня указанного сотрудника, с указанием временной метки начала",
            name = "workingDayCreationDto", required = true,
            example = "{\"startTimeStamp\": \"2021-04-16 08:00:00\", \"profileId\": 1}")
                                                  @RequestBody @Validated WorkingDayCreationDto workingDayCreationDto, BindingResult bindingResult);

    @ApiOperation(value = "Создает новый рабочий день текущего пользователя", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 201, message = "Новый рабочий день успешно создан", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Некорректное тело запроса"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует"),
            @ApiResponse(code = 415, message = "Неподдерживаемый тип данных")
    })
    @PostMapping("/profile/working_day")
    ResponseEntity<?> createWorkingDayForCurrentUser(@ApiParam(value = "JSON представление рабочего дня текущего сотрудника, с указанием временной метки начала",
            name = "workingDayCreationDto", required = true,
            example = "{\"startTimeStamp\": \"2021-04-16 08:00:00\", \"profileId\": 1}")
                                                     @RequestBody @Validated WorkingDayCreationDto workingDayCreationDto, BindingResult bindingResult);

    @ApiOperation(value = "Обновляет рабочий день пользователя с указанным идентификатором", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рабочий день успешно обновлен", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Некорректное тело запроса"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует"),
            @ApiResponse(code = 415, message = "Неподдерживаемый тип данных")
    })
    @PutMapping("/working_day")
    ResponseEntity<?> updateWorkingDayByProfileId(@ApiParam(value = "JSON представление рабочего дня сотрудника", name = "workingDayDto", required = true,
            example = "{\"startTimeStamp\": \"2021-04-16 08:00:00\", \"endTimeStamp\": \"2021-04-16 17:00:00\", " +
                    "\"report\": \"Настройка рабочего места\", \"profileId\": 1}")
                                                  @RequestBody @Validated WorkingDayDto workingDayDto, BindingResult bindingResult);

    @ApiOperation(value = "Обновляет рабочий день текущего пользователя", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рабочий день успешно обновлен", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Некорректное тело запроса"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс отсутствует"),
            @ApiResponse(code = 415, message = "Неподдерживаемый тип данных")
    })
    @PutMapping("/profile/working_day")
    ResponseEntity<?> updateWorkingDayForCurrentUser(@ApiParam(value = "JSON представление рабочего дня сотрудника", name = "workingDayDto", required = true,
            example = "{\"startTimeStamp\": \"2021-04-16 08:00:00\", \"endTimeStamp\": \"2021-04-16 17:00:00\", " +
                    "\"report\": \"Настройка рабочего места\", \"profileId\": 1}")
                                                     @RequestBody @Validated WorkingDayDto workingDayDto, BindingResult bindingResult);
}

