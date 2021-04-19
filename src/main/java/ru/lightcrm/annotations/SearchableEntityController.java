package ru.lightcrm.annotations;

import ru.lightcrm.entities.SearchableEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Помечает контроллер поисковой сущности.
 * Используется как метаинформация для получения url ресурса
 * и передачи его на фронт.
 * Поля обязательные.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchableEntityController {

    /**
     * url ресурса (сущности) на сервере
     */
    String url();

    /**
     * Класс сущности
     */
    Class<? extends SearchableEntity> entityClass();
}
