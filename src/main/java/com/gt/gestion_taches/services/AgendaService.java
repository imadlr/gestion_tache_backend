package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.AgendaDTO;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.exceptions.saveAgendaException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AgendaService {
    List<AgendaDTO> getByResponsibleIdAndDate(Long responsibleId, LocalDate date) throws UserNotFoundException;

    List<AgendaDTO> getByResponsible(Long responsibleId) throws UserNotFoundException;

    AgendaDTO saveAgenda(AgendaDTO agendaDTO) throws saveAgendaException, UserNotFoundException;

    AgendaDTO updateAgenda(AgendaDTO agendaDTO) throws UserNotFoundException;

    void deleteAgenda(Long agendaId);

    void finishedAgenda(Map<String,Long> request);
}
