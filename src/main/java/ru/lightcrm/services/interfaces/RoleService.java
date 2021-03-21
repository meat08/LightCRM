package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.RoleDto;

public interface RoleService {
    RoleDto findByName(String name);
}
