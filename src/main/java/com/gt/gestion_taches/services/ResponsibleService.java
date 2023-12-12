package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.entities.Responsible;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

public interface ResponsibleService {

    PageResponsibleDTO getByCniContains(String keyword, int page, int size);

    ResponsibleDTO saveResponsible(ResponsibleDTO responsibleDTO) throws UserNameExistsException;

    ResponsibleDTO updateResponsible(ResponsibleDTO responsibleDTO);

    void deleteResponsible(Long responsibleId);

    Responsible getResponsible(Long responsibleId) throws UserNotFoundException;
}
