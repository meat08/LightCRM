package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import ru.lightcrm.annotations.SearchableField;
import ru.lightcrm.entities.SearchableEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс, который расширяет стандартный спринговый репозиторий SimpleJpaRepository, для добавления общего метода для всех сущностей, по которым ведется поиск
 *
 * @param <T>  - тип сущности
 * @param <ID> - тип id
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SearchableEntityRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements SearchableEntityRepository<T, ID> {

    private final EntityManager entityManager;

    public SearchableEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * Общий для всех репозиториев метод поиска по фразе, вызывается для сущностей, по которым ведется поиск
     * Поиск ведется без учета регистра
     * Все сущности, по которым ведется поиск, должны быть унаследованы от класса SearchableEntity, у которого есть поле searchIndex
     *
     * @param searchIndex - поисковая фраза
     * @return - результат в виде списка
     */
    @Override
    @Transactional
    public List<T> searchBySearchIndexLike(String searchIndex) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
        Root<T> root = cQuery.from(getDomainClass());
        cQuery
                .select(root)
                .where(builder
                        .like(builder.lower(root.get("searchIndex")), "%" + searchIndex.toLowerCase() + "%"));
        TypedQuery<T> query = entityManager.createQuery(cQuery);
        return query.getResultList();
    }

    /**
     * Перед методами сохранения вызывается метод prepareIndexSearchField
     */
    @Override
    public <S extends T> S save(S entity) {
        prepareIndexSearchField(Optional.ofNullable(entity).orElseThrow(() -> new RuntimeException("Entity must not be null.")));
        return super.save(entity);
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        prepareIndexSearchField(Optional.ofNullable(entity).orElseThrow(() -> new RuntimeException("Entity must not be null.")));
        return super.saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            new RuntimeException("Entity must not be null.");
        }
        for (S entity : entities) {
            prepareIndexSearchField(entity);
        }
        return super.saveAll(entities);
    }

    /**
     * Формирует поле сущности, используемое для поиска, путем конкатенации значений полей, помеченных аннотацией @SearchableField
     * Учитывается порядок полей, если указан в аннотации.
     */
    private <S extends T> void prepareIndexSearchField(S entity) {
        if (entity instanceof SearchableEntity) {
            Map<Integer, String> map = new HashMap<>();
            StringJoiner sj = new StringJoiner(" ");
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(SearchableField.class)) {
                    Integer position = field.getAnnotation(SearchableField.class).position();
                    if (!map.containsKey(position)) {
                        putInMap(entity, map, field, position);
                        continue;
                    }
                    putInMap(entity, map, field, ++position);
                }
            }
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                sj.add(entry.getValue());
            }
            ((SearchableEntity) entity).setSearchIndex(sj.toString());
        }
    }

    private <S extends T> void putInMap(S entity, Map<Integer, String> map, Field field, Integer position) {
        field.setAccessible(true);
        map.put(position, "" + ReflectionUtils.getField(field, entity));
    }
}
