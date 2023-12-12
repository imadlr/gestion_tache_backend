package com.gt.gestion_taches.entities;

import com.gt.gestion_taches.enums.AgendaState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String object;
    private LocalDate date;
    private LocalTime hour;
    private String observation;
    @Enumerated(EnumType.STRING)
    private AgendaState state;
    @ManyToOne
    private Responsible responsible;
}
