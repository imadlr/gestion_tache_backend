package com.gt.gestion_taches.repositories;

import com.gt.gestion_taches.entities.Division;
import com.gt.gestion_taches.entities.Task;
import com.gt.gestion_taches.enums.TaskState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByState(TaskState state);

    List<Task> findByStateAndObjectContains(TaskState state, String keyword);

    List<Task> findByDivisionAndStateAndObjectContains(Division division, TaskState state, String keyword);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.state = :state")
    Long countTasksByState(@Param("state") TaskState state);
}
