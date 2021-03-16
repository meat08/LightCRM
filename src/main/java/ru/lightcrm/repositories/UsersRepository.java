package ru.lightcrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.User;
import ru.lightcrm.entities.dtos.UserDTO;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    //TODO заменить на DTO, для переключения нужно изменить сервис и контроллер.
    Optional<User> findByLogin(String login);
}
