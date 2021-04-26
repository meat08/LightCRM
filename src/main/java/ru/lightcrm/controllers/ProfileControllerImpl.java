package ru.lightcrm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.ProfileController;
import ru.lightcrm.entities.dtos.*;
import ru.lightcrm.services.interfaces.ProfileService;
import ru.lightcrm.services.interfaces.WorkingDayService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {
    private final ProfileService profileService;
    private final WorkingDayService workingDayService;

    @Override
    public ProfileDto getById(Long id) {
        return profileService.findDtoById(id);
    }

    @Override
    public List<ProfileDto> getAll() {
        return profileService.findDtoAll();
    }

    @Override
    public ProfileFullDto getProfileFullById(Long id) {
        return profileService.findFullDtoById(id);
    }

    @Override
    public List<ProfileFullDto> getAllProfilesFull() {
        return profileService.findFullDtoAll();
    }

    @Override
    public ProfileFullDto getProfileFull(Principal principal) {
        return profileService.findFullDtoByUserLogin(principal.getName());
    }

    @Override
    public ProfileMiniDto getProfileMiniById(Long id) {
        return profileService.findMiniDtoById(id);
    }

    @Override
    public List<ProfileMiniDto> getAllProfilesMini() {
        return profileService.findMiniDtoAll();
    }

    @Override
    public ProfileMiniDto getProfileMini(Principal principal) {
        return profileService.findMiniDtoByUserLogin(principal.getName());
    }

    @Override
    public ResponseEntity<?> saveNewUser(SystemUserDto systemUserDto, BindingResult bindingResult) {
        profileService.saveNewUser(systemUserDto, bindingResult);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public WorkingDayDto getCurrentUserWorkingDay(Principal principal, String date) {
        return workingDayService.findByUserLoginAndDate(principal.getName(), date);
    }

    @Override
    public WorkingDayDto getWorkingDayByProfileId(Long id, String date) {
        return workingDayService.findByProfileIdAndDate(id, date);
    }

    @Override
    public ResponseEntity<?> createWorkingDayByProfileId(WorkingDayCreationDto workingDayCreationDto, BindingResult bindingResult) {
        workingDayService.createNewWorkingDate(workingDayCreationDto, bindingResult, false);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> createWorkingDayForCurrentUser(WorkingDayCreationDto workingDayCreationDto, BindingResult bindingResult) {
        workingDayService.createNewWorkingDate(workingDayCreationDto, bindingResult, true);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateWorkingDayByProfileId(WorkingDayDto workingDayDto, BindingResult bindingResult) {
        workingDayService.updateWorkingDay(workingDayDto, bindingResult, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateWorkingDayForCurrentUser(WorkingDayDto workingDayDto, BindingResult bindingResult) {
        workingDayService.updateWorkingDay(workingDayDto, bindingResult, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
