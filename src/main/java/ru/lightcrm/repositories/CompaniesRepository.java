package ru.lightcrm.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.Company;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Long> {
    Optional<Company> findOneByName(@NotNull String name);
    Optional<Company> findOneByInn(@NotNull Long inn);
    Optional<Company> findOneById(@NotNull Long id);
}
