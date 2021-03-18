package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDTO;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.CompaniesRepository;
import ru.lightcrm.services.interfaces.CompaniesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompaniesServiceImpl implements CompaniesService {
    private final CompaniesRepository companiesRepository;

    public CompanyDTO findByName(String name) {
        Company company = companiesRepository.findOneByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format("Компания '%s' не найдена", name)));
        return new CompanyDTO(company);
    }

    public CompanyDTO findByInn(Long inn) {
        Company company = companiesRepository.findOneByInn(inn).orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с ИНН '%s' не найдена", inn)));
        return new CompanyDTO(company);
    }

    public CompanyDTO findById(Long id) {
        Company company = companiesRepository.findOneById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с id '%s' не найдена", id)));
        return new CompanyDTO(company);
    }

    public List<CompanyDTO> findAllDTO() {
        return companiesRepository.findAll().stream().map(CompanyDTO::new).collect(Collectors.toList());
    }
}
