package ru.lightcrm.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.lightcrm.controllers.interfaces.StaffUnitController;
import ru.lightcrm.services.interfaces.StaffUnitService;

@RestController
@RequiredArgsConstructor
public class StaffUnitControllerImpl implements StaffUnitController {

  private final StaffUnitService staffUnitService;

  @Override
  public List<String> getAllStaffUnitNames() {
    return staffUnitService.getNames();
  }
}
