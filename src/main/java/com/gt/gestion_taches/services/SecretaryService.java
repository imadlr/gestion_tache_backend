package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.SecretaryDTO;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

import java.util.List;

public interface SecretaryService {
    List<SecretaryDTO> getByCniContains(String keyword);

    SecretaryDTO saveSecretary(SecretaryDTO secretaryDTO) throws UserNameExistsException;

    SecretaryDTO updateSecretary(SecretaryDTO secretaryDTO);

    void deleteSecretary(Long secretaryId);

    SecretaryDTO getSecretaryDTO(String username) throws UserNotFoundException;
}
