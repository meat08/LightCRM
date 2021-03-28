package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.ProfileController;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.entities.dtos.ProfileFullDto;
import ru.lightcrm.entities.dtos.SystemUserDto;
import ru.lightcrm.entities.dtos.UserDto;
import ru.lightcrm.services.interfaces.ProfileService;
import ru.lightcrm.services.interfaces.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    @Override
    public ProfileDto getById(Long id) {
        return profileService.findById(id);
    }

    @Override
    public List<ProfileDto> getAll() {
        return profileService.findAll();
    }

    @Override
    public ProfileFullDto getProfileFullById(Long id) {
        return profileService.findFullById(id);
    }

    @Override
    public List<ProfileFullDto> getAllProfilesFull() {
        return profileService.findFullAll();
    }

    @Override
    public ProfileFullDto getProfileFull(Principal principal) {
        UserDto user = userService.getByUsername(principal.getName());
        ProfileFullDto profileFullDto = profileService.findFullByUserId(user.getId());
        return profileFullDto;
    }

    @Override
    public ResponseEntity<?> saveNewUser(SystemUserDto systemUserDto, BindingResult bindingResult) {
        profileService.saveNewUser(systemUserDto, bindingResult);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
