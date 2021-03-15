package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.ProfileDto;

import java.util.List;

public interface ProfileService {

    ProfileDto findById(Long id);

    List<ProfileDto> findAll();

}
