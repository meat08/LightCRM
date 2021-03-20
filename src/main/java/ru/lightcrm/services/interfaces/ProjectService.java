package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> findAll();

    ProjectDTO findById(Long id);

    ProjectDTO findOneByName(String name);

    List<ProjectDTO> findByManagerId(Long id);
}
