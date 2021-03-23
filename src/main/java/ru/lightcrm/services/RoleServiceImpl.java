package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.dtos.RoleDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.RoleRepository;
import ru.lightcrm.services.interfaces.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository rolesRepository;

    public RoleDto findByName(String name) {
        Role role = rolesRepository.findOneByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format("Роль '%s' не найдена", name)));
        return new RoleDto(role);
    }
}
