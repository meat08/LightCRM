package ru.lightcrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.dtos.RoleDTO;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String name);
}
