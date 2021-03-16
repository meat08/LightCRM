package ru.lightcrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDTO;
import ru.lightcrm.repositories.CompaniesRepository;
import ru.lightcrm.services.interfaces.CompaniesService;

import java.util.List;
import java.util.Set;

@Service
public class CompaniesServiceImpl implements CompaniesService {
    private CompaniesRepository companiesRepository;

    @Autowired
    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    public CompanyDTO findByName(String name) {
        return companiesRepository.findOneByName(name);
    }

    public CompanyDTO findByInn(Long inn) {
        return companiesRepository.findOneByInn(inn);
    }

    public CompanyDTO findById(Long id) {
        return companiesRepository.findOneById(id);
    }

    public Set<CompanyDTO> findAllDTO() {
        return companiesRepository.findAllBy();
    }
}
