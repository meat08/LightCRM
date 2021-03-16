package ru.lightcrm.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.CompaniesController;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDTO;
import ru.lightcrm.services.CompaniesServiceImpl;
import ru.lightcrm.services.interfaces.CompaniesService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompaniesControllerImpl implements CompaniesController {

    private CompaniesService companiesService;

    @Autowired
    public void setCompaniesRepository(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    @GetMapping
    public Set<CompanyDTO> getCompanyContent() {
        return companiesService.findAllDTO();
    }
}
