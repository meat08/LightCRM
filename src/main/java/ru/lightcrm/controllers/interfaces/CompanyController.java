package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDto;

import java.util.List;

@Api(value = "Компании")
public interface CompanyController {
    @GetMapping("/api/v1/companies")
    @ApiOperation(value = " Возвращает cписок DTO компаний",
            notes = "Запрос списка компаний",
            httpMethod = "GET"
    )
    List<CompanyDto> getCompanyContent();

}
