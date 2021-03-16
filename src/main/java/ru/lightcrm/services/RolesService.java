package ru.lightcrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Role;
import ru.lightcrm.repositories.RolesRepository;

@Service
public class RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Role findByName(String name) {
        return rolesRepository.findOneByName(name);
    }
}
