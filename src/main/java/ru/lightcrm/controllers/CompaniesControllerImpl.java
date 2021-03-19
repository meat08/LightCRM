package ru.lightcrm.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.CompaniesController;
import ru.lightcrm.entities.dtos.CompanyDTO;
import ru.lightcrm.services.interfaces.CompaniesService;

import java.util.List;

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
