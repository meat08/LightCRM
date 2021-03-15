package ru.lightcrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Company;
import ru.lightcrm.repositories.CompaniesRepository;

import java.util.List;

@Service
public class CompaniesService {
    private CompaniesRepository companiesRepository;

    @Autowired
    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    public Company findByName(String name) {
        return companiesRepository.findOneByName(name);
    }

    public Company findByInn(Long inn) {
        return companiesRepository.findOneByInn(inn);
    }

    public Company findById(Long id) {
        return companiesRepository.findOneById(id);
    }

    public List<Company> findAll() {
        return companiesRepository.findAll();
    }
}
