package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.dtos.SearchItemDto;

import java.util.List;

public interface SearchableEntityService {

    List<SearchItemDto> getSearchResults(String searchIndex);
}
