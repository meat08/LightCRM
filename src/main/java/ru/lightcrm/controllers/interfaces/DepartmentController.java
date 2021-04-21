package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import ru.lightcrm.entities.dtos.DepartmentDto;

@Api(value = "/api/v1/departments", tags = "Контроллер для работы с отделами", produces = "application/json")
@RequestMapping(value = "/api/v1/departments", produces = "application/json")
public interface DepartmentController {

  @ApiOperation(value = "Возвращает список названий всех отделов", httpMethod = "GET", produces = "application/json", response = String.class, responseContainer = "List")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List"),
      @ApiResponse(code = 401, message = "Клиент не авторизован"),
      @ApiResponse(code = 403, message = "Нет прав"),
  })

  @GetMapping("/names")
  List<String> getAllDepartmentNames();

  @GetMapping("/departments")
  List<DepartmentDto> getAllDepartments();

  @GetMapping(value = "/{id}", produces = "application/json")
  DepartmentDto getDepartmentById(@ApiParam(value = "идентификатор отдела", required = true, example = "1") @PathVariable Long id);

  @PostMapping(consumes = "application/json", produces = "application/json")
  DepartmentDto saveDepartment(@ApiParam(value = "JSON представление данных нового отдела", required = true) @RequestBody DepartmentDto departmentDto);

  @PutMapping(consumes = "application/json", produces = "application/json")
  DepartmentDto updateDepartment(@ApiParam(value = "JSON представление данных измененного отдела", required = true) @RequestBody DepartmentDto departmentDto);

  @DeleteMapping(value = "/{id}")
  void deleteDepartmentById(@ApiParam(value = "идентификатор удаляемого отдела", required = true, example = "1") @PathVariable Long id);

}
