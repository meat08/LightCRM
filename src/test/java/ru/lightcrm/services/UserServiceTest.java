package ru.lightcrm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.User;
import ru.lightcrm.repositories.UsersRepository;
import ru.lightcrm.services.interfaces.UserService;

import java.util.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    public void getByUsernameTest() {
        Mockito.doReturn(generateMockUser()).when(usersRepository)
                .findByLogin("user");

        User user = userService.getByUsername("user").orElse(null);
        Assertions.assertNotNull(user);
        Assertions.assertTrue(user.getEnabled());
        Assertions.assertEquals(1, user.getPriorities().size());
        Assertions.assertEquals("100", user.getPassword());
    }

    @Test
    public void loadUserByUsernameTest() {
        Mockito.doReturn(generateMockUser()).when(usersRepository)
                .findByLogin("user");

        UserDetails userDetails = userService.loadUserByUsername("user");
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals("user", userDetails.getUsername());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());
    }

    private Optional<User> generateMockUser() {
        User user = new User();
        Priority priority = new Priority();
        priority.setId(1L);
        priority.setName("TEST");
        priority.setRoles(null);
        Set<Priority> priorities = new HashSet<>(1);
        priorities.add(priority);
        user.setId(1L);
        user.setLogin("user");
        user.setPassword("100");
        user.setEnabled(true);
        user.setPriorities(priorities);
        return Optional.of(user);
    }
}
