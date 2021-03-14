package ru.lightcrm.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lightcrm.entities.Company;
import ru.lightcrm.services.CompaniesService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompaniesController {

    private CompaniesService companiesService;

    @GetMapping
    public List<Company> getCartContent() {
        return companiesService.findAll();
    }}
