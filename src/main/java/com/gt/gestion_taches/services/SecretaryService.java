package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.PageSecretaryDTO;
import com.gt.gestion_taches.dtos.SecretaryDTO;
import com.gt.gestion_taches.exceptions.UserNameExistsException;

public interface SecretaryService {
    PageSecretaryDTO getByCniContains(String keyword, int page, int size);

    SecretaryDTO saveSecretary(SecretaryDTO secretaryDTO) throws UserNameExistsException;

    SecretaryDTO updateSecretary(SecretaryDTO secretaryDTO);

    void deleteSecretary(Long secretaryId);
}
