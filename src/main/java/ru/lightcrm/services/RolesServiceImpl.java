package ru.lightcrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.dtos.RoleDTO;
import ru.lightcrm.repositories.RolesRepository;
import ru.lightcrm.services.interfaces.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Role findByName(String name) {
        return rolesRepository.findOneByName(name);
    }
}
