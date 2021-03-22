package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto findByName(String name);
    CompanyDto findByInn(Long inn);
    CompanyDto findById(Long id);
    List<CompanyDto> findAllDTO();
}
