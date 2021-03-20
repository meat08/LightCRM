package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.dtos.RoleDTO;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.RolesRepository;
import ru.lightcrm.services.interfaces.RolesService;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {
    private final RolesRepository rolesRepository;

    public RoleDTO findByName(String name) {
        Role role = rolesRepository.findOneByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format("Роль '%s' не найдена", name)));
        return new RoleDTO (role);
    }
}
