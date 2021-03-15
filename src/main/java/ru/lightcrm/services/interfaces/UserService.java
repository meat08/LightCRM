package ru.lightcrm.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.lightcrm.entities.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    //TODO изменить на DTO
    Optional<User> getByUsername(String username);
    void saveUser(User user);

}
