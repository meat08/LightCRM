package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDto;

import java.util.List;

@Api(value = "/api/v1/companies", tags = "Контроллер для работы с компаниями", produces = "application/json")
public interface CompanyController {
    @GetMapping("/api/v1/companies")
    @ApiOperation(value = " Возвращает cписок DTO компаний",
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

}
