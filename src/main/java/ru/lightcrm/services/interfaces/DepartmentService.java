package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> findAll();

    DepartmentDTO findById(Long id);

    DepartmentDTO findOneByName(String name);

    DepartmentDTO findOneByLeaderId(Long id);
}
