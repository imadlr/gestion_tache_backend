package com.gt.gestion_taches.web;

import com.gt.gestion_taches.dtos.AgendaDTO;
import com.gt.gestion_taches.dtos.PageTaskDTO;
import com.gt.gestion_taches.dtos.TaskDTO;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.servicesImpl.AgendaServiceImpl;
import com.gt.gestion_taches.servicesImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sec")
@AllArgsConstructor
public class SecretaryRestController {
    private TaskServiceImpl taskService;
    private AgendaServiceImpl agendaService;

    @GetMapping("/completedTasks")
    public PageTaskDTO getCompletedTasks(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        return taskService.getByStateAndObjectContains(TaskState.TERMINEE, keyword, page, size);
    }

    @GetMapping("/currentTasks")
    public PageTaskDTO getCurrentTasks(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        return taskService.getByStateAndObjectContains(TaskState.EN_ATTENTE, keyword, page, size);
    }

    @GetMapping("/lateTasks")
    public PageTaskDTO getLateTasks(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        return taskService.getByStateAndObjectContains(TaskState.EN_RETARD, keyword, page, size);
    }

    @PostMapping("/saveTask")
    public TaskDTO saveTask(@RequestBody TaskDTO taskDTO) throws UserNotFoundException {
        return taskService.saveTask(taskDTO);
    }

    @PutMapping("/updateTask")
    public TaskDTO updateTask(@RequestBody TaskDTO taskDTO) throws UserNotFoundException {
        return taskService.updateTask(taskDTO);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping("/agenda/{responsibleId}")
    public List<AgendaDTO> getAgenda(@PathVariable Long responsibleId) throws UserNotFoundException {
        return agendaService.getByResponsible(responsibleId);
    }

    @PostMapping("/saveAgenda")
    public AgendaDTO saveAgenda(@RequestBody AgendaDTO agendaDTO) throws UserNotFoundException {
        return agendaService.saveAgenda(agendaDTO);
    }

    @PutMapping("/updateAgenda")
    public AgendaDTO updateAgenda(@RequestBody AgendaDTO agendaDTO) throws UserNotFoundException {
        return agendaService.updateAgenda(agendaDTO);
    }

    @DeleteMapping("/deleteAgenda/{agendaId}")
    public void deleteAgenda(@PathVariable Long agendaId) {
        agendaService.deleteAgenda(agendaId);
    }

}
