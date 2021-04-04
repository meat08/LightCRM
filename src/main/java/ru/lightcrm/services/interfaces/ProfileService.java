package ru.lightcrm.services.interfaces;

import org.springframework.validation.BindingResult;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.entities.dtos.ProfileFullDto;
import ru.lightcrm.entities.dtos.SystemUserDto;

import java.util.List;

public interface ProfileService {

    ProfileDto findDtoById(Long id);

    Profile findById(Long id);

    Profile findByUserLogin(String login);

    List<ProfileDto> findDtoAll();

    ProfileFullDto findFullDtoById(Long id);

    List<ProfileFullDto> findFullDtoAll();

    ProfileFullDto findFullDtoByUserId(Long userId);

    void saveProfile(Profile profile);

    void saveNewUser(SystemUserDto systemUserDto, BindingResult bindingResult);
}
