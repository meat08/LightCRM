package ru.lightcrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.StaffUnit;
import ru.lightcrm.entities.dtos.StaffUnitDTO;

@Repository
public interface StaffUnitsRepository extends CrudRepository<StaffUnit, Long> {
    StaffUnit findOneByName(String name);
}
