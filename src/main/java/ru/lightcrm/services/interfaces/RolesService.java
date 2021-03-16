package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.dtos.RoleDTO;

public interface RolesService {
    Role findByName(String name);
}
