package com.gt.gestion_taches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageTaskDTO {
    private List<TaskDTO> taskDTOS;
    private int currentPage;
    private int totalPages;
    private int pageSize;
}
