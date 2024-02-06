package com.gt.gestion_taches.web;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.servicesImpl.DivisionServiceImpl;
import com.gt.gestion_taches.servicesImpl.ResponsibleServiceImpl;
import com.gt.gestion_taches.servicesImpl.SecretaryServiceImpl;
import com.gt.gestion_taches.servicesImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminRestController {

    private TaskServiceImpl taskService;
    private DivisionServiceImpl divisionService;
    private ResponsibleServiceImpl responsibleService;
    private SecretaryServiceImpl secretaryService;

    @GetMapping("/currentTasks")
    public List<TaskDTO> getCurrentTasks() {
        return taskService.getTasksByState(TaskState.EN_ATTENTE);
    }

    @GetMapping("/completedTasks")
    public List<TaskDTO> getCompletedTasks() {
        return taskService.getTasksByState(TaskState.TERMINEE);
    }

    @GetMapping("/lateTasks")
    public List<TaskDTO> getLateTasks() {
        return taskService.getTasksByState(TaskState.EN_RETARD);
    }

    @GetMapping("/divisions")
    public List<DivisionDTO> getDivisions(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return divisionService.getByCniContains(keyword);
    }

    @PostMapping("/saveDivision")
    public DivisionDTO saveDivision(@RequestBody DivisionDTO divisionDTO) throws UserNameExistsException {
        return divisionService.saveDivision(divisionDTO);
    }

    @PutMapping("/updateDivision")
    public DivisionDTO updateDivision(@RequestBody DivisionDTO divisionDTO) {
        return divisionService.updateDivision(divisionDTO);
    }

    @DeleteMapping("/deleteDivision/{divisionId}")
    public void deleteDivision(@PathVariable Long divisionId) {
        divisionService.deleteDivision(divisionId);
    }

    @GetMapping("/responsibles")
    public List<ResponsibleDTO> getResponsibles(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return responsibleService.getByCniContains(keyword);
    }

    @PostMapping("/saveResponsible")
    public ResponsibleDTO saveResponsible(@RequestBody ResponsibleDTO responsibleDTO) throws UserNameExistsException {
        return responsibleService.saveResponsible(responsibleDTO);
    }

    @PutMapping("/updateResponsible")
    public ResponsibleDTO updateResponsible(@RequestBody ResponsibleDTO responsibleDTO) {
        return responsibleService.updateResponsible(responsibleDTO);
    }

    @DeleteMapping("/deleteResponsible/{responsibleId}")
    public void deleteResponsible(@PathVariable Long responsibleId) {
        responsibleService.deleteResponsible(responsibleId);
    }

    @GetMapping("/secretaries")
    public List<SecretaryDTO> getSecretaries(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return secretaryService.getByCniContains(keyword);
    }

    @PostMapping("/saveSecretary")
    public SecretaryDTO saveSecretary(@RequestBody SecretaryDTO secretaryDTO) throws UserNameExistsException {
        return secretaryService.saveSecretary(secretaryDTO);
    }

    @PutMapping("/updateSecretary")
    public SecretaryDTO updateSecretary(@RequestBody SecretaryDTO secretaryDTO) {
        return secretaryService.updateSecretary(secretaryDTO);
    }

    @DeleteMapping("/deleteSecretary/{secretaryId}")
    public void deleteSecretary(@PathVariable Long secretaryId) {
        secretaryService.deleteSecretary(secretaryId);
    }
}
