package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDTO;

import java.util.List;
import java.util.Set;

@Api(value = "Компании")
public interface CompaniesController {
    @GetMapping("/api/v1/companies")
    @ApiOperation(value = " Возвращает cписок DTO компаний",
            notes = "Запрос списка компаний",
            httpMethod = "GET"
    )
    List<CompanyDTO> getCompanyContent();

}
