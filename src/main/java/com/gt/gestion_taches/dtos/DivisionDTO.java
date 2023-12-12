package com.gt.gestion_taches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DivisionDTO {
    private Long id;
    private String nameDivision;
    private String firstName;
    private String lastName;
    private String cni;
    private UserAccountDTO userAccountDTO;
}
