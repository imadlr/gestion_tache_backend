package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.entities.Responsible;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

import java.util.List;

public interface ResponsibleService {

    List<ResponsibleDTO> getByCniContains(String keyword);

    ResponsibleDTO saveResponsible(ResponsibleDTO responsibleDTO) throws UserNameExistsException;

    ResponsibleDTO updateResponsible(ResponsibleDTO responsibleDTO);

    void deleteResponsible(Long responsibleId);

    Responsible getResponsible(Long responsibleId) throws UserNotFoundException;

    ResponsibleDTO getResponsibleDTO(String username) throws UserNotFoundException;

    List<ResponsibleDTO> getResponsibles();
}
