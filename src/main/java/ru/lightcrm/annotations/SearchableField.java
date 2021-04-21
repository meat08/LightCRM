package ru.lightcrm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Помечает поля, по которым должен вестись поиск.
 * При сохранении сущности, поля помеченные данной аннотацией, конкатенируются в общее поле searchIndex (другие сущности в качестве полей не поддерживаются),
 * унаследованное от класса SearchableEntity, по которому ведется поиск.
 * Необязательное поле position для указания порядка сохранения помеченных полей в поле searchIndex.
 * Если не указывать position, сохраняются в порядке объявления в классе
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchableField {

    int position() default 0;
}
