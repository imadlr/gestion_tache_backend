package com.gt.gestion_taches.web;

import com.gt.gestion_taches.dtos.DivisionDTO;
import com.gt.gestion_taches.dtos.TaskDTO;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.servicesImpl.DivisionServiceImpl;
import com.gt.gestion_taches.servicesImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/division")
@AllArgsConstructor
public class DivisionRestController {
    private TaskServiceImpl taskService;
    private DivisionServiceImpl divisionService;

    @GetMapping("/getByUsername")
    public DivisionDTO getDivisionByUsername(@RequestParam String username) throws UserNotFoundException {
        return divisionService.getDivisionDTO(username);
    }

    @GetMapping("/completedTasks/{divisionId}")
    public List<TaskDTO> getCompletedTasks(@PathVariable Long divisionId,
                                           @RequestParam(name = "keyword", defaultValue = "") String keyword) throws UserNotFoundException {
        return taskService.getByDivisionIdAndStateAndObjectContains(divisionId, TaskState.TERMINEE, keyword);
    }

    @GetMapping("/currentTasks/{divisionId}")
    public List<TaskDTO> getCurrentTasks(@PathVariable Long divisionId,
                                       @RequestParam(name = "keyword", defaultValue = "") String keyword) throws UserNotFoundException {
        return taskService.getByDivisionIdAndStateAndObjectContains(divisionId, TaskState.EN_ATTENTE, keyword);
    }

    @GetMapping("/lateTasks/{divisionId}")
    public List<TaskDTO> getLateTasks(@PathVariable Long divisionId,
                                    @RequestParam(name = "keyword", defaultValue = "") String keyword
                                    ) throws UserNotFoundException {
        return taskService.getByDivisionIdAndStateAndObjectContains(divisionId, TaskState.EN_RETARD, keyword);
    }

    @PutMapping("/finishedTask")
    public void finishedTask(@RequestBody Map<String,Long> request) {
        taskService.finishedTask(request);
    }

}
