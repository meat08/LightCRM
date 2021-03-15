package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.repositories.ProfileRepository;
import ru.lightcrm.services.interfaces.ProfileService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public ProfileDto findById(Long id) {
        return new ProfileDto(profileRepository.findById().get());
    }

    @Override
    public List<ProfileDto> findAll() {
        return profileRepository.findAll().stream.map(ProfileDto::new).collect(Collectors.toList());
    }
}
