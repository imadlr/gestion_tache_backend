package com.gt.gestion_taches.dtos;

import com.gt.gestion_taches.enums.AgendaState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {
    private Long id;
    private String object;
    private LocalDate date;
    private LocalTime hour;
    private String observation;
    private AgendaState state;
    private Long idResponsible;
}
