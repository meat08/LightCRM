package ru.lightcrm.services.interfaces;

import org.springframework.validation.BindingResult;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.entities.dtos.ProfileFullDto;
import ru.lightcrm.entities.dtos.SystemUserDto;

import java.util.List;

public interface ProfileService {

    ProfileDto findById(Long id);

    Profile findEntityById(Long id);

    List<ProfileDto> findAll();

    ProfileFullDto findFullById(Long id);

    List<ProfileFullDto> findFullAll();

    ProfileFullDto findFullByUserId(Long userId);

    ProfileDto findByLogin(String login);

    void saveProfile(Profile profile);

    void saveNewUser(SystemUserDto systemUserDto, BindingResult bindingResult);
}
