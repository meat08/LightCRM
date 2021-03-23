package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.dtos.ProjectDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.ProjectRepository;
import ru.lightcrm.services.interfaces.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(ProjectDto::new).collect(Collectors.toList());
    }

    @Override
    public ProjectDto findById(Long id) {
        return new ProjectDto(projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с id = %s не найден", id))));
    }

    @Override
    public ProjectDto findOneByName(String name) {
        return new ProjectDto(projectRepository.findOneByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с наименованием = \"%s\" не найден", name))));
    }

    @Override
    public List<ProjectDto> findByManagerId(Long id) {
        return projectRepository.findByManagerId(id).stream().map(ProjectDto::new).collect(Collectors.toList());
    }
}
