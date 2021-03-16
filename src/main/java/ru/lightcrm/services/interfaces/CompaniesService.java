package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDTO;

import java.util.List;
import java.util.Set;

public interface CompaniesService {
    CompanyDTO findByName(String name);
    CompanyDTO findByInn(Long inn);
    CompanyDTO findById(Long id);
    Set<CompanyDTO> findAllDTO();
}
