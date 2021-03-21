package ru.lightcrm.controllers;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.ProfileController;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.entities.dtos.UserDTO;
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
    public ProfileDto getProfile(Principal principal) {
        UserDTO user = userService.getByUsername(principal.getName());
        ProfileDto profileDto = profileService.findByUserId(user.getId());
        return profileDto;
    }

    @Override
    @GetMapping
    public List<ProfileDto> getAll() {
        return profileService.findAll();
    }
}
