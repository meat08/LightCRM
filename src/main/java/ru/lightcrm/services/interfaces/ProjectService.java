package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAll();

    ProjectDto findById(Long id);

    ProjectDto findOneByName(String name);

    List<ProjectDto> findByManagerId(Long id);
}
