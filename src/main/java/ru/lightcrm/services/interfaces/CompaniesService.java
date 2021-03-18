package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.CompanyDTO;

import java.util.List;

public interface CompaniesService {
    CompanyDTO findByName(String name);
    CompanyDTO findByInn(Long inn);
    CompanyDTO findById(Long id);
    List<CompanyDTO> findAllDTO();
}
