package ru.lightcrm.controllers;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.ProfileController;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.entities.dtos.ProfileFullDto;
import ru.lightcrm.entities.dtos.UserDto;
import ru.lightcrm.services.interfaces.ProfileService;

import java.util.List;
import ru.lightcrm.services.interfaces.UserService;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = "application/json")
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    @Override
    @GetMapping(value = "/{id}")
    public ProfileDto getById(@PathVariable Long id) {
        return profileService.findById(id);
    }

    @Override
    @GetMapping("/profile")
    public ProfileFullDto getProfileFull(Principal principal) {
        UserDto user = userService.getByUsername(principal.getName());
        ProfileFullDto profileFullDto= profileService.findFullByUserId(user.getId());
        return profileFullDto;
    }

    @Override
    @GetMapping
    public List<ProfileDto> getAll() {
        return profileService.findAll();
    }

    @Override
    @GetMapping(value = "/full/{id}")
    public ProfileFullDto getProfileFullById(@PathVariable Long id) {
        return profileService.findFullById(id);
    }

    @Override
    @GetMapping(value = "/full")
    public List<ProfileFullDto> getAllProfilesFull() {
        return profileService.findFullAll();
    }
}
