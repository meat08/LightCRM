package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto findByName(String name);
    CompanyDto findByInn(Long inn);
    CompanyDto findById(Long id);
    Company findEntityById(Long id);
    List<CompanyDto> findAllDTO();
    CompanyDto save(CompanyDto companyDto);
    CompanyDto update(CompanyDto companyDto);
    void deleteById(Long id);
}
