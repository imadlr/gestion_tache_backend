package com.gt.gestion_taches.repositories;

import com.gt.gestion_taches.entities.Agenda;
import com.gt.gestion_taches.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByResponsibleAndDate(Responsible responsible, LocalDate date);

    List<Agenda> findByResponsible(Responsible responsible);

}
