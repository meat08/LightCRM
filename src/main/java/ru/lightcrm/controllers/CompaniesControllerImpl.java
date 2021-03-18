package ru.lightcrm.controllers;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CompaniesControllerImpl implements CompaniesController {

    private final CompaniesService companiesService;

    @GetMapping
    public List<CompanyDTO> getCompanyContent() {
        return companiesService.findAllDTO();
    }
}
