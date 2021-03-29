package ru.lightcrm.controllers.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "/api/v1/staff_units", tags = "Контроллер для работы с должностями", produces = "application/json")
@RequestMapping(value = "/api/v1/staff_units", produces = "application/json")
public interface StaffUnitController {

  @ApiOperation(value = "Возвращает список названий всех должностей", httpMethod = "GET", produces = "application/json", response = String.class, responseContainer = "List")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List"),
      @ApiResponse(code = 401, message = "Клиент не авторизован"),
      @ApiResponse(code = 403, message = "Нет прав"),
  })
  @GetMapping("/names")
  List<String> getAllStaffUnitNames();

}
