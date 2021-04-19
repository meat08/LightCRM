package ru.lightcrm.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.DepartmentController;
import ru.lightcrm.entities.dtos.DepartmentDto;
import ru.lightcrm.services.interfaces.DepartmentService;

@RestController
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {

  private final DepartmentService departmentService;

  @Override
  public List<String> getAllDepartmentNames() {
    return departmentService.getAllDepartmentNames();
  }

  @Override
  public List<DepartmentDto> getAllDepartments() {
    return departmentService.findAll();
  }

  @Override
  public DepartmentDto getDepartmentById(Long id) {
    return departmentService.findDtoById(id);
  }

  @Override
  public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
    departmentDto.setId(null);
    return departmentService.saveOrUpdate(departmentDto);
  }

  @Override
  public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
    return departmentService.saveOrUpdate(departmentDto);
  }

  @Override
  public void deleteDepartmentById(Long id) {
    departmentService.deleteById(id);
  }
}

