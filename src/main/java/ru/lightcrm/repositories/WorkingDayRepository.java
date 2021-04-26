package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.lightcrm.entities.WorkingDay;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface WorkingDayRepository extends JpaRepository<WorkingDay, Long> {

    @Query(value = "SELECT wd.id, wd.start_timestamp, wd.end_timestamp, wd.report, wd.profile_id " +
                   "FROM working_days wd WHERE date(wd.start_timestamp) = ?1\\:\\:date",
           nativeQuery = true)
    List<WorkingDay> findByDate(@NotNull String workingDayDate);

    @Query(value = "SELECT wd.id, wd.start_timestamp, wd.end_timestamp, wd.report, wd.profile_id " +
                   "FROM users u INNER JOIN profiles p ON u.id = p.user_id " +
                   "INNER JOIN working_days wd on p.id = wd.profile_id WHERE u.login = ?1 " +
                   "AND date(wd.start_timestamp) = ?2\\:\\:date",
           nativeQuery = true)
    Optional<WorkingDay> findByUserLoginAndDate(@NotNull String login, @NotNull String date);

    @Query(value = "SELECT wd.id, wd.start_timestamp, wd.end_timestamp, wd.report, wd.profile_id " +
                   "FROM profiles p INNER JOIN working_days wd ON p.id = wd.profile_id " +
                   "WHERE p.id = ?1 AND date(wd.start_timestamp) = ?2\\:\\:date", nativeQuery = true)
    Optional<WorkingDay> findByProfileIdAndDate(@NotNull Long profileId, @NotNull String workingDayDate);

    @Modifying
    @Query(value = "UPDATE working_days SET end_timestamp = ?3 FROM profiles p INNER JOIN working_days wd " +
                   "ON p.id = wd.profile_id WHERE p.id = ?1 AND date(wd.start_timestamp) = ?2\\:\\:date",
           nativeQuery = true)
    Optional<WorkingDay> setEndDateByProfileIdAndDate(@NotNull Long profileId, @NotNull String workingDayDate, @NotNull String endDate);
}
