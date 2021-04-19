package ru.lightcrm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Содержит поле, по которому выполняется поиск.
 * Сущности, для которых требуется выполнять поиск
 * должны быть унаследованы от данного класса.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class SearchableEntity extends BaseEntity {

    @Column(name = "search_index")
    public String searchIndex;

    public SearchableEntity(Long id) {
        super(id);
    }
}
