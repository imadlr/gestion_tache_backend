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

@RestController
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class AdminRestController {

    private TaskServiceImpl taskService;
    private DivisionServiceImpl divisionService;
    private ResponsibleServiceImpl responsibleService;
    private SecretaryServiceImpl secretaryService;

    @GetMapping("/currentTasks")
    public PageTaskDTO getCurrentTasks(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return taskService.getTasksByState(TaskState.EN_ATTENTE, page, size);
    }

    @GetMapping("/completedTasks")
    public PageTaskDTO getCompletedTasks(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return taskService.getTasksByState(TaskState.TERMINEE, page, size);
    }

    @GetMapping("/lateTasks")
    public PageTaskDTO getLateTasks(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return taskService.getTasksByState(TaskState.EN_RETARD, page, size);
    }

    @GetMapping("/divisions")
    public PageDivisionDTO getDivisions(@RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return divisionService.getByCniContains(keyword, page, size);
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
    public PageResponsibleDTO getResponsibles(@RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return responsibleService.getByCniContains(keyword, page, size);
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
    public PageSecretaryDTO getSecretaries(@RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return secretaryService.getByCniContains(keyword, page, size);
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
