package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lightcrm.annotations.SearchableEntitiesController;
import ru.lightcrm.annotations.SearchableEntityController;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.Contact;
import ru.lightcrm.entities.dtos.CompanyDto;
import ru.lightcrm.entities.dtos.ContactDto;

import java.util.List;

@SearchableEntitiesController(value = {
        @SearchableEntityController(url = "/api/v1/companies", entityClass = Company.class),
        @SearchableEntityController(url = "", entityClass = Contact.class)
})
@Api(value = "/api/v1/companies", tags = "Контроллер для работы с компаниями", produces = "application/json")
@RequestMapping(value = "/api/v1/companies", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CompanyController {

    @GetMapping()
    @ApiOperation(value = "Возвращает cписок DTO компаний",
            notes = "Запрос списка компаний",
            httpMethod = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompanyDto.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    List<CompanyDto> getCompanyContent();

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Возвращает DTO компании по Id",
            notes = "Запрос информации о компании",
            httpMethod = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompanyDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    CompanyDto getCompany(@PathVariable Long id);

    @PostMapping
    @ApiOperation(value = "Возвращает DTO созданной компании",
            notes = "Сохранение новой компании",
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompanyDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    CompanyDto saveCompany(@RequestBody CompanyDto companyDto);

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Возращает статус-код операции",
            notes = "Удаление компании",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    void delete(@PathVariable Long id);

    @PutMapping
    @ApiOperation(value = "Возвращает DTO измененной компании",
            notes = "Изменение существуюшей компании",
            httpMethod = "PUT"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CompanyDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    CompanyDto updateCompany(@RequestBody CompanyDto companyDto);

    @DeleteMapping(value = "/contact/{id}")
    @ApiOperation(value = "Возращает статус-код операции",
            notes = "Удаление контакта",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    void deleteContact(@PathVariable Long id);

    @PutMapping("/contact")
    @ApiOperation(value = "Возвращает DTO измененной компании",
            notes = "Изменение существуюшей компании",
            httpMethod = "PUT"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ContactDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    ContactDto updateContact(@RequestBody ContactDto contactDto);

    @PostMapping("/contact")
    @ApiOperation(value = "Возвращает DTO измененной компании",
            notes = "Изменение существуюшей компании",
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ContactDto.class),
            @ApiResponse(code = 401, message = "Клиент не авторизован"),
            @ApiResponse(code = 403, message = "Нет прав"),
            @ApiResponse(code = 404, message = "Ресурс не найден")
    })
    ContactDto saveContact(@RequestBody ContactDto contactDto);
}
