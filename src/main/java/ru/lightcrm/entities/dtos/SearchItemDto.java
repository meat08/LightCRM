package ru.lightcrm.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.lightcrm.entities.SearchableEntity;
import ru.lightcrm.utils.SearchUtil;

import java.util.Optional;

@ApiModel(description = "Класс, содержащий результаты поиска")
@Data
@JsonRootName("SearchItemDto")
public class SearchItemDto {

    @ApiModelProperty(notes = "URL сущности", example = "api/v1/profiles/1")
    @JsonProperty("url")
    private String url;

    @ApiModelProperty(notes = "Название сущности (Class Name), по которой ведется поиск", example = "Profile", position = 1)
    @JsonProperty("entityName")
    private String entityName;

    @ApiModelProperty(notes = "Конкатенированные поисковые поля сущности", required = true, position = 2)
    @JsonProperty("indexData")
    private String indexData;

    public SearchItemDto(SearchableEntity entity) {
        this.url = SearchUtil.getUrl(entity) != null ? SearchUtil.getUrl(entity) + entity.getId() : null;
        this.entityName = Optional.ofNullable(SearchUtil.getLocalizedEntityName(entity.getClass().getSimpleName()))
                .orElse(entity.getClass().getSimpleName());
        this.indexData = entity.getSearchIndex();
    }
}
