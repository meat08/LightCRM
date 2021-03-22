package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();

    DepartmentDto findById(Long id);

    DepartmentDto findOneByName(String name);

    DepartmentDto findOneByLeaderId(Long id);
}
