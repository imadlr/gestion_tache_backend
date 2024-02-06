package com.gt.gestion_taches.web;

import com.gt.gestion_taches.dtos.AgendaDTO;
import com.gt.gestion_taches.dtos.CountTaskByStateDTO;
import com.gt.gestion_taches.dtos.TaskDTO;
import com.gt.gestion_taches.enums.TaskState;
import com.gt.gestion_taches.exceptions.NoAgendaFoundException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.servicesImpl.AgendaServiceImpl;
import com.gt.gestion_taches.servicesImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/resp")
@AllArgsConstructor
public class ResponsibleRestController {
    private AgendaServiceImpl agendaService;
    private TaskServiceImpl taskService;

    @GetMapping("/countByState")
    public List<CountTaskByStateDTO> getCountOfTasksByState() {
        return taskService.getCountOfTasksByState();
    }

    @GetMapping("/agendaByDay/{responsibleId}")
    public List<AgendaDTO> getAgendaByDay(@PathVariable Long responsibleId,
                                          @RequestParam(name = "jour", defaultValue = "") String jour) throws UserNotFoundException, NoAgendaFoundException {
        if (jour.isEmpty()) {
            // Utiliser la date d'aujourd'hui comme valeur par défaut
            LocalDate dateAujourdhui = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
            jour = dateAujourdhui.format(formatter);
        }
        return agendaService.getByResponsibleIdAndDay(responsibleId, jour);
    }

    @GetMapping("/agendaByDate/{responsibleId}")
    public List<AgendaDTO> getAgendaByDate(@PathVariable Long responsibleId, @RequestParam LocalDate date) throws UserNotFoundException {
        return agendaService.getByResponsibleIdAndDate(responsibleId, date);
    }

    @PutMapping("/finishedAgenda/{agendaId}")
    public void finishedAgenda(@PathVariable Long agendaId) {
        agendaService.finishedAgenda(agendaId);
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

}
