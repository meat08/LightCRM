package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lightcrm.entities.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findOneByName(String name);

    @Query("SELECT d FROM Department d WHERE d.leader.id = ?1")
    Optional<Department> findOneByLeaderId(Long id);
}
