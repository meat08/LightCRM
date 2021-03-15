package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lightcrm.entities.Profile;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {
    @Query("SELECT p FROM Profile p " +
            "JOIN FETCH p.user " +
            "JOIN FETCH p.staffUnit " +
            "JOIN FETCH p.managedDepartment " +
            "JOIN FETCH p.departments " +
            "JOIN FETCH p.company " +
            "JOIN FETCH p.comments " +
            "WHERE p.user.login = ?1")
    Optional<Profile> findByLogin(String login);

    @Query("SELECT p FROM Profile p " +
            "JOIN FETCH p.user " +
            "JOIN FETCH p.staffUnit " +
            "JOIN FETCH p.managedDepartment " +
            "JOIN FETCH p.departments " +
            "JOIN FETCH p.company " +
            "JOIN FETCH p.comments " +
            "WHERE p.id = ?1")
    Optional<Profile> findById(Long id);

    @Query("SELECT p FROM Profile p " +
            "JOIN FETCH p.user " +
            "JOIN FETCH p.staffUnit " +
            "JOIN FETCH p.managedDepartment " +
            "JOIN FETCH p.departments " +
            "JOIN FETCH p.company " +
            "JOIN FETCH p.comments")
    List<Profile> findAll();
}
