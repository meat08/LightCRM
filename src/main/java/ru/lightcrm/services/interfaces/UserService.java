package ru.lightcrm.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.lightcrm.entities.User;
import ru.lightcrm.entities.dtos.UserDto;

public interface UserService extends UserDetailsService {
    UserDto getByUsername(String username);
    void saveUser(User user);
    boolean isPresent(String login);
}
