package ru.lightcrm.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.CompanyController;
import ru.lightcrm.entities.dtos.CompanyDto;
import ru.lightcrm.services.interfaces.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getCompanyContent() {
        return companyService.findAllDTO();
    }

    @Override
    public CompanyDto getCompany(Long id) {
        return companyService.findById(id);
    }

    @Override
    public CompanyDto saveCompany(CompanyDto companyDto) {
        companyDto.setId(null);
        return companyService.save(companyDto);
    }

    @Override
    public void delete(Long id) {
        companyService.deleteById(id);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        return companyService.update(companyDto);
    }
}
