package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.Profile;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ProfileRepository extends SearchableEntityRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {
    @Query("SELECT p FROM Profile p WHERE p.user.login = ?1")
    Optional<Profile> findByLogin(@NotNull String login);

    Optional<Profile> findByUserId(@NotNull Long userId);

}
