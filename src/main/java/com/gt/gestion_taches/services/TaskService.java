package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.CountTaskByStateDTO;
import com.gt.gestion_taches.dtos.TaskDTO;
import com.gt.gestion_taches.dtos.PageTaskDTO;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.SaveTaskException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

import java.util.List;

public interface TaskService {
    PageTaskDTO getTasksByState(TaskState state, int page, int size);

    PageTaskDTO getByStateAndObjectContains(TaskState state, String keyword, int page, int size);

    PageTaskDTO getByDivisionIdAndStateAndObjectContains(Long divisionId, TaskState state, String keyword, int page, int size) throws UserNotFoundException;

    TaskDTO saveTask(TaskDTO taskDTO) throws SaveTaskException, UserNotFoundException;

    TaskDTO updateTask(TaskDTO taskDTO) throws UserNotFoundException;

    void deleteTask(Long taskId);

    void finishedTask(Long taskId);

    List<CountTaskByStateDTO> getCountOfTasksByState();
}
