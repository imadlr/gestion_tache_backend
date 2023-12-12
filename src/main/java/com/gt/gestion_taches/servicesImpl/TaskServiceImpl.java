package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.CountTaskByStateDTO;
import com.gt.gestion_taches.dtos.TaskDTO;
import com.gt.gestion_taches.dtos.PageTaskDTO;
import com.gt.gestion_taches.entities.Division;
import com.gt.gestion_taches.entities.Task;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.TaskRepository;
import com.gt.gestion_taches.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private MapperServiceImpl mapper;

    private DivisionServiceImpl divisionService;

    @Override
    public PageTaskDTO getTasksByState(TaskState state, int page, int size) {
        Page<Task> tasks = taskRepository.findTasksByState(state, PageRequest.of(page, size));
        List<TaskDTO> taskDTOS = tasks.getContent().stream().map(t -> mapper.fromTask(t)).toList();
        PageTaskDTO pageTaskDTO = new PageTaskDTO();
        pageTaskDTO.setTaskDTOS(taskDTOS);
        pageTaskDTO.setTotalPages(tasks.getTotalPages());
        pageTaskDTO.setCurrentPage(page);
        pageTaskDTO.setPageSize(size);
        return pageTaskDTO;
    }

    @Override
    public PageTaskDTO getByStateAndObjectContains(TaskState state, String keyword, int page, int size) {
        Page<Task> tasks = taskRepository.findByStateAndObjectContains(state, keyword, PageRequest.of(page, size));
        List<TaskDTO> taskDTOS = tasks.getContent().stream().map(t -> mapper.fromTask(t)).toList();
        PageTaskDTO pageTaskDTO = new PageTaskDTO();
        pageTaskDTO.setTaskDTOS(taskDTOS);
        pageTaskDTO.setTotalPages(tasks.getTotalPages());
        pageTaskDTO.setCurrentPage(page);
        pageTaskDTO.setPageSize(size);
        return pageTaskDTO;
    }

    @Override
    public PageTaskDTO getByDivisionIdAndStateAndObjectContains(Long divisionId, TaskState state, String keyword, int page, int size) throws UserNotFoundException {
        Division division = divisionService.getDivision(divisionId);
        Page<Task> tasks = taskRepository.findByDivisionAndStateAndObjectContains(division, state, keyword, PageRequest.of(page, size));
        List<TaskDTO> taskDTOS = tasks.getContent().stream().map(t -> mapper.fromTask(t)).toList();
        PageTaskDTO pageTaskDTO = new PageTaskDTO();
        pageTaskDTO.setTaskDTOS(taskDTOS);
        pageTaskDTO.setTotalPages(tasks.getTotalPages());
        pageTaskDTO.setCurrentPage(page);
        pageTaskDTO.setPageSize(size);
        return pageTaskDTO;
    }

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) throws UserNotFoundException {
        Division division = divisionService.getDivision(taskDTO.getDivisionId());
        Task task = mapper.fromTaskDTO(taskDTO);
        task.setDivision(division);
        return mapper.fromTask(taskRepository.save(task));

    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) throws UserNotFoundException {
        Division division = divisionService.getDivision(taskDTO.getDivisionId());
        Task task = mapper.fromTaskDTO(taskDTO);
        task.setDivision(division);
        return mapper.fromTask(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void finishedTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        task.setState(TaskState.TERMINEE);
        taskRepository.save(task);
    }

    @Override
    public List<CountTaskByStateDTO> getCountOfTasksByState() {
        CountTaskByStateDTO completed = new CountTaskByStateDTO();
        completed.setState(TaskState.TERMINEE);
        completed.setCount(taskRepository.countTasksByState(TaskState.TERMINEE));
        CountTaskByStateDTO current = new CountTaskByStateDTO();
        current.setState(TaskState.EN_ATTENTE);
        current.setCount(taskRepository.countTasksByState(TaskState.EN_ATTENTE));
        CountTaskByStateDTO late = new CountTaskByStateDTO();
        late.setState(TaskState.EN_RETARD);
        late.setCount(taskRepository.countTasksByState(TaskState.EN_RETARD));
        return List.of(completed, current, late);
    }

}
