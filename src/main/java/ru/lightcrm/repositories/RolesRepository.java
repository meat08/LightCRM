package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lightcrm.entities.Role;

import java.util.Optional;


public interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role>  findOneByName(String name);
}
