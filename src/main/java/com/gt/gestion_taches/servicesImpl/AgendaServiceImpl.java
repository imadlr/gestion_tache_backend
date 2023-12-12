package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.AgendaDTO;
import com.gt.gestion_taches.entities.Agenda;
import com.gt.gestion_taches.entities.Responsible;
import com.gt.gestion_taches.enums.AgendaState;
import com.gt.gestion_taches.exceptions.NoAgendaFoundException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.AgendaRepository;
import com.gt.gestion_taches.repositories.ResponsibleRepository;
import com.gt.gestion_taches.services.AgendaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class AgendaServiceImpl implements AgendaService {
    private AgendaRepository agendaRepository;
    private ResponsibleServiceImpl responsibleService;
    private MapperServiceImpl mapper;


    @Override
    public List<AgendaDTO> getByResponsibleIdAndDate(Long responsibleId, LocalDate date) throws UserNotFoundException {
        Responsible responsible = responsibleService.getResponsible(responsibleId);
        return agendaRepository.findByResponsibleAndDate(responsible, date).stream().map(ag -> mapper.fromAgenda(ag)).toList();
    }

    @Override
    public List<AgendaDTO> getByResponsible(Long responsibleId) throws UserNotFoundException {
        Responsible responsible = responsibleService.getResponsible(responsibleId);
        List<AgendaDTO> agendaDTOS = agendaRepository.findByResponsible(responsible).stream().map(ag -> {
            AgendaDTO agendaDTO = mapper.fromAgenda(ag);
            agendaDTO.setIdResponsible(ag.getResponsible().getId());
            return agendaDTO;
        }).toList();
        return agendaDTOS;
    }

    @Override
    public AgendaDTO saveAgenda(AgendaDTO agendaDTO) throws UserNotFoundException {
        Responsible responsible = responsibleService.getResponsible(agendaDTO.getIdResponsible());
        Agenda agenda = mapper.fromAgendaDTO(agendaDTO);
        agenda.setResponsible(responsible);
        AgendaDTO savedAgenda = mapper.fromAgenda(agendaRepository.save(agenda));
        savedAgenda.setIdResponsible(responsible.getId());
        return savedAgenda;
    }

    @Override
    public AgendaDTO updateAgenda(AgendaDTO agendaDTO) throws UserNotFoundException {
        Responsible responsible = responsibleService.getResponsible(agendaDTO.getIdResponsible());
        Agenda agenda = mapper.fromAgendaDTO(agendaDTO);
        agenda.setResponsible(responsible);
        AgendaDTO savedAgenda = mapper.fromAgenda(agendaRepository.save(agenda));
        savedAgenda.setIdResponsible(responsible.getId());
        return savedAgenda;
    }

    @Override
    public void deleteAgenda(Long agendaId) {
        agendaRepository.deleteById(agendaId);
    }

    public List<AgendaDTO> getByResponsibleIdAndDay(Long responsibleId, String jour) throws UserNotFoundException, NoAgendaFoundException {
        Responsible responsible = responsibleService.getResponsible(responsibleId);
        List<Agenda> agendas = (List<Agenda>) responsible.getAgendas();
        // Obtenez la date actuelle.
        LocalDate currentDate = LocalDate.now();
        // Convertissez le jour en majuscules pour ignorer la casse.
        jour = jour.toUpperCase();
        // Déterminez le jour de la semaine correspondant à la chaîne entrée.
        DayOfWeek dayOfWeek = switch (jour) {
            case "LUNDI" -> DayOfWeek.MONDAY;
            case "MARDI" -> DayOfWeek.TUESDAY;
            case "MERCREDI" -> DayOfWeek.WEDNESDAY;
            case "JEUDI" -> DayOfWeek.THURSDAY;
            case "VENDREDI" -> DayOfWeek.FRIDAY;
            case "SAMEDI" -> DayOfWeek.SATURDAY;
            case "DIMANCHE" -> DayOfWeek.SUNDAY;
            default -> null;
        };
        if (dayOfWeek == null) {
            throw new NoAgendaFoundException("Aucune Agenda trouvée pour ce jour dans cette semaine");
        }
        // Calculate the date of the corresponding day of the week.
        LocalDate targetDate = currentDate.with(TemporalAdjusters.nextOrSame(dayOfWeek));
        // Filter the agendas based on the target date.
        List<Agenda> filteredAgendas = new ArrayList<>();
        for (Agenda agenda : agendas) {
            if (agenda.getDate().isEqual(targetDate)) {
                filteredAgendas.add(agenda);
            }
        }

        return filteredAgendas.stream().map(ag -> mapper.fromAgenda(ag)).toList();
    }

    @Override
    public void finishedAgenda(Long agendaId) {
        Agenda agenda = agendaRepository.findById(agendaId).orElse(null);
        agenda.setState(AgendaState.TERMINEE);
        agendaRepository.save(agenda);
    }
}
