package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.CountTaskByStateDTO;
import com.gt.gestion_taches.dtos.TaskDTO;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.SaveTaskException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasksByState(TaskState state);

    List<TaskDTO> getByStateAndObjectContains(TaskState state, String keyword);

    List<TaskDTO> getByDivisionIdAndStateAndObjectContains(Long divisionId, TaskState state, String keyword) throws UserNotFoundException;

    TaskDTO saveTask(TaskDTO taskDTO) throws SaveTaskException, UserNotFoundException;

    TaskDTO updateTask(TaskDTO taskDTO) throws UserNotFoundException;

    void deleteTask(Long taskId);

    void finishedTask(Long taskId);

    List<CountTaskByStateDTO> getCountOfTasksByState();
}
