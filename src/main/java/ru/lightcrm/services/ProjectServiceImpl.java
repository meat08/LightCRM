package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.dtos.ProjectDTO;
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
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findById(Long id) {
        return new ProjectDTO(projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с id = %s не найден", id))));
    }

    @Override
    public ProjectDTO findOneByName(String name) {
        return new ProjectDTO(projectRepository.findOneByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Проект с наименованием = \"%s\" не найден", name))));
    }

    @Override
    public List<ProjectDTO> findByManagerId(Long id) {
        return projectRepository.findByManagerId(id).stream().map(ProjectDTO::new).collect(Collectors.toList());
    }
}
