package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.ProfileController;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.services.interfaces.ProfileService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = "application/json")
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {
    private final ProfileService profileService;

    @Override
    @GetMapping(value = "/{id}")
    public ProfileDto getById(@PathVariable Long id) {
        return profileService.findById(id);
    }

    @Override
    @GetMapping
    public List<ProfileDto> getAll() {
        return profileService.findAll();
    }
}
