package com.gt.gestion_taches.dtos;

import com.gt.gestion_taches.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {
    private Long id;
    private String object;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TaskState state;
    private String nameDivision;
    private Long divisionId;
}
