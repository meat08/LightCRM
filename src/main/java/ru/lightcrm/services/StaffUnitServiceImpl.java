package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.StaffUnit;
import ru.lightcrm.entities.dtos.StaffUnitDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.StaffUnitRepository;
import ru.lightcrm.services.interfaces.StaffUnitService;

@Service
@RequiredArgsConstructor
public class StaffUnitServiceImpl implements StaffUnitService {
    private final StaffUnitRepository staffUnitRepository;

    @Override
    public StaffUnit findByName(String name) {
        return staffUnitRepository.findOneByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format("Должность с названием %s не найдена", name)));
    }

    @Override
    public StaffUnitDto findDtoByName(String name) {
        return new StaffUnitDto(findByName(name));
    }
}
