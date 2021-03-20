package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.dtos.DepartmentDTO;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.DepartmentRepository;
import ru.lightcrm.services.interfaces.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream().map(DepartmentDTO::new).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        return new DepartmentDTO(departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Отдел с id = %s не найден", id))));
    }

    @Override
    public DepartmentDTO findOneByName(String name) {
        return new DepartmentDTO(departmentRepository.findOneByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Отдел с наименованием = \"%s\" не найден", name))));
    }

    @Override
    public DepartmentDTO findOneByLeaderId(Long id) {
        return new DepartmentDTO(departmentRepository.findOneByLeaderId(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Отдел руководителя (id = %d) не найден", id))));
    }
}
