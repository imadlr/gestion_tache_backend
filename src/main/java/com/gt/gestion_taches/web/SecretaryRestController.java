package com.gt.gestion_taches.web;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.services.DivisionService;
import com.gt.gestion_taches.services.ResponsibleService;
import com.gt.gestion_taches.services.SecretaryService;
import com.gt.gestion_taches.servicesImpl.AgendaServiceImpl;
import com.gt.gestion_taches.servicesImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/sec")
@AllArgsConstructor
public class SecretaryRestController {

    private TaskServiceImpl taskService;
    private AgendaServiceImpl agendaService;
    private SecretaryService secretaryService;
    private DivisionService divisionService;
    private ResponsibleService responsibleService;

    @GetMapping("/getByUsername")
    public SecretaryDTO getSecretaryByUsername(@RequestParam String username) throws UserNotFoundException {
        return secretaryService.getSecretaryDTO(username);
    }

    @GetMapping("/completedTasks")
    public List<TaskDTO> getCompletedTasks(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return taskService.getByStateAndObjectContains(TaskState.TERMINEE, keyword);
    }

    @GetMapping("/currentTasks")
    public List<TaskDTO> getCurrentTasks(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return taskService.getByStateAndObjectContains(TaskState.EN_ATTENTE, keyword);
    }

    @GetMapping("/lateTasks")
    public List<TaskDTO> getLateTasks(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return taskService.getByStateAndObjectContains(TaskState.EN_RETARD, keyword);
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

    @GetMapping("/agendaByDate/{responsibleId}")
    public List<AgendaDTO> getAgendaByDate(@PathVariable Long responsibleId, @RequestParam LocalDate date) throws UserNotFoundException {
        return agendaService.getByResponsibleAndDate(responsibleId,date);
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

    @GetMapping("/divisions")
    public List<DivisionDTO> getDivisions() {
        return divisionService.getDivisions();
    }

    @GetMapping("/responsibles")
    public List<ResponsibleDTO> getResponsibles() {
        return responsibleService.getResponsibles();
    }

}