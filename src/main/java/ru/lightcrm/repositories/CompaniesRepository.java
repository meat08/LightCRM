package ru.lightcrm.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lightcrm.entities.Company;
import java.util.List;
import java.util.Optional;


public interface CompaniesRepository extends JpaRepository<Company, Long> {
    Optional<Company> findOneByName(String name);
    Optional<Company> findOneByInn(Long inn);
    Optional<Company> findOneById(Long id);
    List<Company> findAll();
}
