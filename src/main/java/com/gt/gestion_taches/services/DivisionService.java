package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.DivisionDTO;
import com.gt.gestion_taches.entities.Division;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

import java.util.List;

public interface DivisionService {
    List<DivisionDTO> getByCniContains(String keyword);

    Division getDivision(Long divisionId) throws UserNotFoundException;

    DivisionDTO getDivisionDTO(String username) throws UserNotFoundException;

    DivisionDTO saveDivision(DivisionDTO divisionDTO) throws UserNameExistsException;

    DivisionDTO updateDivision(DivisionDTO divisionDTO) throws UserNotFoundException;

    void deleteDivision(Long divisionId);

    List<DivisionDTO> getDivisions();

}
