package ru.lightcrm.services.interfaces;

import org.springframework.validation.BindingResult;
import ru.lightcrm.entities.WorkingDay;
import ru.lightcrm.entities.dtos.WorkingDayCreationDto;
import ru.lightcrm.entities.dtos.WorkingDayDto;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface WorkingDayService {

    WorkingDay findById(@NotNull Long id);

    List<WorkingDayDto> findByDate(@NotNull String date);

    WorkingDayDto findByUserLoginAndDate(@NotNull String login, @NotNull String date);

    WorkingDayDto findByProfileIdAndDate(@NotNull Long profileId, @NotNull String date);

    void createNewWorkingDate(@NotNull WorkingDayCreationDto workingDayCreationDto, @NotNull BindingResult bindingResult, boolean needExistCheck);

    void updateWorkingDay(@NotNull WorkingDayDto workingDayDto, @NotNull BindingResult bindingResult, boolean needExistCheck);
}
