package ru.lightcrm.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.lightcrm.entities.User;
import ru.lightcrm.entities.dtos.UserDTO;

public interface UserService extends UserDetailsService {
    UserDTO getByUsername(String username);
    void saveUser(User user);

}
