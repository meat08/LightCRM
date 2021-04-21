package ru.lightcrm.services;

import org.springframework.stereotype.Service;
import ru.lightcrm.entities.SearchableEntity;
import ru.lightcrm.entities.dtos.SearchItemDto;
import ru.lightcrm.repositories.SearchableEntityRepository;
import ru.lightcrm.services.interfaces.SearchableEntityService;
import ru.lightcrm.utils.SearchUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SearchableEntityServiceImpl implements SearchableEntityService {

    /**
     * Вызывает общий метод поиска по фразе репозиториев всех поисковых сущностей
     *
     * @param searchIndex - фраза для поиска
     * @return - список результатов
     */
    @Override
    public List<SearchItemDto> getSearchResults(String searchIndex) {
        if (searchIndex.isBlank()) {
            return Collections.emptyList();
        }
//        if (searchIndex.trim().contains(" ")) {
//            return getSearchResultSplit(searchIndex);
//        }
        List<SearchItemDto> foundList = new ArrayList<>();
        for (SearchableEntityRepository<?, ?> rep : SearchUtil.getSearchableEntityRepositories()) {
            rep.searchBySearchIndexLike(searchIndex).stream()
                    .filter(o -> o instanceof SearchableEntity)
                    .map(o -> (SearchableEntity) o)
                    .map(SearchItemDto::new)
                    .forEach(foundList::add);
        }
        return foundList;
    }

    /**
     * Для поиска по фразам, разделенным пробелом
     * Не тестировал
     */
    private List<SearchItemDto> getSearchResultSplit(String searchIndex) {
        List<SearchItemDto> foundList = new ArrayList<>();
        for (String searchText : searchIndex.split(" ")) {
            foundList.addAll(getSearchResults(searchText.trim()));
        }
        return foundList;
    }
}
