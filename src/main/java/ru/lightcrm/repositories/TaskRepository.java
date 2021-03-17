package ru.lightcrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lightcrm.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findOneByName(String name);

    @Query("SELECT t FROM Task t WHERE t.producer.id = ?1")
    List<Task> findByProducerId(Long id);

    @Query("SELECT t FROM Task t WHERE t.producer.id = ?1 AND t.taskState.id = ?2")
    List<Task> findByProducerIdAndTaskStateId(Long producerId, Long taskStateId);

    @Query("SELECT t FROM Task t WHERE t.responsible.id = ?1")
    List<Task> findByResponsibleId(Long id);

    @Query("SELECT t FROM Task t WHERE t.responsible.id = ?1 AND t.taskState.id = ?2")
    List<Task> findByResponsibleIdAndTaskStateId(Long responsibleId, Long taskStateId);

    @Query("SELECT t FROM Task t WHERE t.project.id = ?1")
    List<Task> findByProjectId(Long id);
}
