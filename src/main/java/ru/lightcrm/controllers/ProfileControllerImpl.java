package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.ProfileController;
import ru.lightcrm.entities.Department;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.StaffUnit;
import ru.lightcrm.entities.User;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.entities.dtos.ProfileFullDto;
import ru.lightcrm.entities.dtos.SystemUserDto;
import ru.lightcrm.entities.dtos.UserDto;
import ru.lightcrm.exceptions.LightCrmError;
import ru.lightcrm.services.interfaces.DepartmentService;
import ru.lightcrm.services.interfaces.ProfileService;
import ru.lightcrm.services.interfaces.StaffUnitService;
import ru.lightcrm.services.interfaces.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ProfileControllerImpl implements ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;
    private final StaffUnitService staffUnitService;

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
        if (userService.isPresent(systemUserDto.getLogin())) {
            log.warn("User with login: {} already exists", systemUserDto.getLogin());
            return new ResponseEntity<>(
                    new LightCrmError(HttpStatus.BAD_REQUEST.value(),
                            String.format("Пользователь с логином %s уже существует", systemUserDto.getLogin())),
                    HttpStatus.BAD_REQUEST);
        }

        if (!systemUserDto.getPassword().equals(systemUserDto.getConfirmationPassword())) {
            log.warn("Password and confirmation password is not equal");
            return new ResponseEntity<>(
                    new LightCrmError(HttpStatus.BAD_REQUEST.value(),
                            "Пароль и подтверждающий пароль не совпадают"),
                    HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(
                    new LightCrmError(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining(System.lineSeparator()))),
                    HttpStatus.BAD_REQUEST);
        }

        User newUser = User.createNewUser(systemUserDto.getLogin(), passwordEncoder.encode(systemUserDto.getPassword()));

        StaffUnit staffUnit = staffUnitService.findByName(systemUserDto.getStaffUnitName());

        List<Department> departments = systemUserDto.getDepartmentNames().stream()
                .map(departmentService::findOneByName)
                .collect(Collectors.toList());

        Profile newProfile = Profile.createNewProfileForUserRegistration(systemUserDto, newUser, staffUnit, departments);

        userService.saveUser(newUser);
        profileService.saveProfile(newProfile);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
