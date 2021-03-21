package ru.lightcrm.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.CompanyController;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDto;
import ru.lightcrm.services.interfaces.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companiesService;

    @GetMapping
    public List<CompanyDto> getCompanyContent() {
        return companiesService.findAllDTO();
    }
}
