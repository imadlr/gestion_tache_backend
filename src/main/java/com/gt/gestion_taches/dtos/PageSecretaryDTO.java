package com.gt.gestion_taches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageSecretaryDTO {
    private List<SecretaryDTO> secretaryDTOS;
    private int currentPage;
    private int totalPages;
    private int pageSize;
}
