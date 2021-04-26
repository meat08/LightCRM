package ru.lightcrm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.dtos.WorkingDayCreationDto;

import javax.persistence.*;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "working_days")
@NoArgsConstructor
@AllArgsConstructor
public class WorkingDay extends BaseEntity {

    @Column(name = "start_timestamp")
    private OffsetDateTime startTimeStamp;

    @Column(name = "end_timestamp")
    private OffsetDateTime endTimeStamp;

    @JoinColumn(name = "report")
    private String report;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public static WorkingDay createNewWorkingDay(WorkingDayCreationDto workingDayCreationDto, Profile profile) {
        WorkingDay workingDay = new WorkingDay();
        workingDay.setStartTimeStamp(workingDayCreationDto.getStartTimeStamp());
        workingDay.setProfile(profile);

        return workingDay;
    }
}
