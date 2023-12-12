package com.gt.gestion_taches.dtos;

import com.gt.gestion_taches.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountTaskByStateDTO {
    private TaskState state;
    private Long count;
}
