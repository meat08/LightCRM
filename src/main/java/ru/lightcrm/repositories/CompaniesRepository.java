package ru.lightcrm.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.dtos.CompanyDTO;

import java.util.List;
import java.util.Set;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Long> {
    CompanyDTO findOneByName(String name);
    CompanyDTO findOneByInn(Long inn);
    CompanyDTO findOneById(Long id);
    Set<CompanyDTO> findAllBy();
}
