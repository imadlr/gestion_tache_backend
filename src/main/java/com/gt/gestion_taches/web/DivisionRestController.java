package com.gt.gestion_taches.web;

import com.gt.gestion_taches.dtos.PageTaskDTO;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.servicesImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/division")
@AllArgsConstructor
public class DivisionRestController {
    private TaskServiceImpl taskService;

    @GetMapping("/completedTasks/{divisionId}")
    public PageTaskDTO getCompletedTasks(@PathVariable Long divisionId,
                                         @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size) throws UserNotFoundException {
        return taskService.getByDivisionIdAndStateAndObjectContains(divisionId, TaskState.TERMINEE, keyword, page, size);
    }

    @GetMapping("/currentTasks/{divisionId}")
    public PageTaskDTO getCurrentTasks(@PathVariable Long divisionId,
                                       @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "10") int size) throws UserNotFoundException {
        return taskService.getByDivisionIdAndStateAndObjectContains(divisionId, TaskState.EN_ATTENTE, keyword, page, size);
    }

    @GetMapping("/lateTasks/{divisionId}")
    public PageTaskDTO getLateTasks(@PathVariable Long divisionId,
                                    @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size) throws UserNotFoundException {
        return taskService.getByDivisionIdAndStateAndObjectContains(divisionId, TaskState.EN_RETARD, keyword, page, size);
    }

    @PutMapping("/finishedTask/{taskId}")
    public void finishedTask(@PathVariable Long taskId) {
        taskService.finishedTask(taskId);
    }
}
