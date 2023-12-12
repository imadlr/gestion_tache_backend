package com.gt.gestion_taches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDivisionDTO {
    private List<DivisionDTO> divisionDTOS;
    private int currentPage;
    private int totalPages;
    private int pageSize;
}
