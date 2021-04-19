package ru.lightcrm.annotations;

import ru.lightcrm.entities.SearchableEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Помечает контроллер поисковой сущности.
 * Используется для указания url и класса сущности.
 * Поля обязательные.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchableController {

    String url();

    Class<? extends SearchableEntity> entityClass();
}
