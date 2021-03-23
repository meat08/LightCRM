package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.CompanyRepository;
import ru.lightcrm.services.interfaces.CompanyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companiesRepository;

    public CompanyDto findByName(String name) {
        Company company = companiesRepository.findOneByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format("Компания '%s' не найдена", name)));
        return new CompanyDto(company);
    }

    public CompanyDto findByInn(Long inn) {
        Company company = companiesRepository.findOneByInn(inn).orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с ИНН '%s' не найдена", inn)));
        return new CompanyDto(company);
    }

    public CompanyDto findById(Long id) {
        Company company = companiesRepository.findOneById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с id '%s' не найдена", id)));
        return new CompanyDto(company);
    }

    public List<CompanyDto> findAllDTO() {
        return companiesRepository.findAll().stream().map(CompanyDto::new).collect(Collectors.toList());
    }
}
