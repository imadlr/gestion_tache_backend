package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.DivisionDTO;
import com.gt.gestion_taches.dtos.PageDivisionDTO;
import com.gt.gestion_taches.entities.Division;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

public interface DivisionService {
    PageDivisionDTO getByCniContains(String keyword, int page, int size);

    Division getDivision(Long divisionId) throws UserNotFoundException;

    DivisionDTO getDivisionDTO(String username) throws UserNotFoundException;

    DivisionDTO saveDivision(DivisionDTO divisionDTO) throws UserNameExistsException;

    DivisionDTO updateDivision(DivisionDTO divisionDTO) throws UserNotFoundException;

    void deleteDivision(Long divisionId);

}
