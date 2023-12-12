package com.gt.gestion_taches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponsibleDTO{
    private Long id;
    private String firstName;
    private String lastName;
    private String cni;
    private UserAccountDTO userAccountDTO;
}
