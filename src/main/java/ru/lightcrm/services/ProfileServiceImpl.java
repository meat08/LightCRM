package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import ru.lightcrm.entities.Department;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.StaffUnit;
import ru.lightcrm.entities.User;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.entities.dtos.ProfileFullDto;
import ru.lightcrm.entities.dtos.SystemUserDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.ProfileRepository;
import ru.lightcrm.services.interfaces.DepartmentService;
import ru.lightcrm.services.interfaces.ProfileService;
import ru.lightcrm.services.interfaces.StaffUnitService;
import ru.lightcrm.services.interfaces.UserService;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;
    private final StaffUnitService staffUnitService;

    @Override
    public Profile findEntityById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Профиль с id %s отсутствует", id)));
    }

    @Override
    public ProfileDto findById(Long id) {
        return new ProfileDto(profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Профиль с id %s отсутствует", id))));
    }

    @Override
    public List<ProfileDto> findAll() {
        return profileRepository.findAll().stream().map(ProfileDto::new).collect(Collectors.toList());
    }

    @Override
    public ProfileFullDto findFullById(Long id) {
        return new ProfileFullDto(profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Профиль с id %s отсутствует", id))));
    }

    @Override
    public List<ProfileFullDto> findFullAll() {
        return profileRepository.findAll().stream().map(ProfileFullDto::new).collect(Collectors.toList());
    }

    @Override
    public ProfileFullDto findFullByUserId(Long userId) {
        return new ProfileFullDto(
                profileRepository.findByUserId(userId).orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Профиль с user id %d отсутствует", userId))));
    }

    @Override
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void saveNewUser(SystemUserDto systemUserDto, BindingResult bindingResult) {
        log.info("Запрос на регистрацию нового пользователя");

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
            log.warn(message);
            throw new ValidationException(message);
        }

        if (!systemUserDto.getPassword().equals(systemUserDto.getConfirmationPassword())) {
            String message = "Пароль и подтверждающий пароль не совпадают";
            log.warn(message);
            throw new ValidationException(message);
        }

        if (userService.isPresent(systemUserDto.getLogin())) {
            String message = String.format("Пользователь с логином: %s уже существует", systemUserDto.getLogin());
            log.warn(message);
            throw new ValidationException(message);
        }

        User newUser = User.createNewUser(systemUserDto.getLogin(), passwordEncoder.encode(systemUserDto.getPassword()));

        StaffUnit staffUnit = staffUnitService.findByName(systemUserDto.getStaffUnitName());

        List<Department> departments = systemUserDto.getDepartmentNames().stream()
                .map(departmentService::findOneByName)
                .collect(Collectors.toList());

        Profile newProfile = Profile.createNewProfileForUserRegistration(systemUserDto, newUser, staffUnit, departments);

        userService.saveUser(newUser);
        saveProfile(newProfile);

        log.info("Пользователь с логином: {} успешно зарегистрирован", systemUserDto.getLogin());
    }
}
