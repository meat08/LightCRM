package ru.lightcrm.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.Company;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Long> {
    Company findOneByName(String name);
    Company findOneByInn(Long inn);
    Company findOneById(Long id);
}
