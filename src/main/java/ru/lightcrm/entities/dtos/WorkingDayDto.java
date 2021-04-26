package ru.lightcrm.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.WorkingDay;
import ru.lightcrm.utils.CustomDateDeserializer;

import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(description = "Класс, представляющий завершенный рабочий день сотрудника", parent = WorkingDayCreationDto.class)
@JsonRootName("WorkingDayDto")
public class WorkingDayDto extends WorkingDayCreationDto {

    @ApiModelProperty(notes = "Временная метка окончания рабочего дня", required = true, position = 3)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonProperty("endTimeStamp")
    private OffsetDateTime endTimeStamp;

    @ApiModelProperty(notes = "Краткий отчет о рабочем дене", dataType = "String", required = true, position = 4)
    @JsonProperty("report")
    private String report;

    public WorkingDayDto(WorkingDay workingDay) {
        super(workingDay);
        this.endTimeStamp = workingDay.getEndTimeStamp();
        this.report = workingDay.getReport();
    }
}
