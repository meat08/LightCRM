package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.dtos.RoleDTO;

public interface RolesService {
    RoleDTO findByName(String name);
}
