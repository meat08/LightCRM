package ru.lightcrm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.dtos.RoleDto;
import ru.lightcrm.repositories.RoleRepository;
import ru.lightcrm.services.interfaces.RoleService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @MockBean
    private RoleRepository rolesRepository;

    private static final String ROLE_NAME = "Администратор";

    private Optional<Role> initMockRole(Long id) {
        Role role = new Role();
        role.setId(id);
        role.setName(ROLE_NAME);

        Set<Priority> priorities = new HashSet<>(1);
        Priority priority = new Priority();
        priority.setId(1L);
        priority.setName("TEST");
        priorities.add(priority);
        role.setPriorities(priorities);

        return Optional.of(role);
    }

    @Test
    void findByName() {
        Long id = 1L;
        final String name = ROLE_NAME;
        Mockito.doReturn(initMockRole(id)).when(rolesRepository).findOneByName(name);

        RoleDto roleDto = roleService.findByName(name);
        Assertions.assertNotNull(roleDto);
        Assertions.assertEquals(id, roleDto.getId());
        Assertions.assertEquals(ROLE_NAME, roleDto.getName());
        Assertions.assertEquals(1L,  roleDto.getPriorities().size());
    }
}