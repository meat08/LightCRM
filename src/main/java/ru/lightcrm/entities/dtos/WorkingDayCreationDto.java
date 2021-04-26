package ru.lightcrm.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lightcrm.entities.WorkingDay;
import ru.lightcrm.utils.CustomDateDeserializer;

import javax.validation.constraints.Min;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@ApiModel(description = "Класс, представляющий рабочий день сотрудника, с указанием временной метки начала", subTypes = WorkingDayDto.class)
@JsonRootName("WorkingDayCreationDto")
public class WorkingDayCreationDto {

    @ApiModelProperty(notes = "Уникальный идентификатор рабочего дня", dataType = "Long", example = "1")
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(notes = "Временная метка начала рабочего дня", dataType = "OffsetDateTime", required = true, position = 1)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonProperty("startTimeStamp")
    private OffsetDateTime startTimeStamp;

    @Min(value = 1, message = "Некорректный id пользователя")
    @ApiModelProperty(notes = "Уникальный идентификатор сотрудника, к которому привязан рабочий день", dataType = "Long", example = "1", required = true, position = 2)
    @JsonProperty("profileId")
    private Long profileId;

    public WorkingDayCreationDto(WorkingDay workingDay) {
        this.id = workingDay.getId();
        this.startTimeStamp = workingDay.getStartTimeStamp();
        this.profileId = workingDay.getProfile().getId();
    }
}
