package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.entities.Secretary;
import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.SecretaryRepository;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import com.gt.gestion_taches.services.SecretaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class SecretaryServiceImpl implements SecretaryService {
    private SecretaryRepository secretaryRepository;
    private UserAccountRepository userAccountRepository;
    private MapperServiceImpl mapper;
    private GenerateIdService generateIdService;

    @Override
    public List<SecretaryDTO> getByCniContains(String keyword) {
        List<Secretary> secretaries = secretaryRepository.findByCniContains(keyword);
        return secretaries.stream().map(sec -> mapper.fromSecretary(sec)).toList();
    }

    @Override
    public SecretaryDTO saveSecretary(SecretaryDTO secretaryDTO) throws UserNameExistsException {
        UserAccount checkUser = userAccountRepository.findByUsername(secretaryDTO.getUserAccountDTO().getUsername());
        if (checkUser != null) {
            throw new UserNameExistsException("Utilisateur Déja Existe");
        } else {
            Secretary secretary = mapper.fromSecretaryDTO(secretaryDTO);
            secretary.setId(generateIdService.getGeneratedId());
            Secretary savedSecretary = secretaryRepository.save(secretary);
            UserAccount userAccount = mapper.fromUserAccountDTO(secretaryDTO.getUserAccountDTO());
            userAccount.setUser(savedSecretary);
            userAccount.setRole("SECRETARY");
            savedSecretary.setUserAccount(userAccountRepository.save(userAccount));
            return mapper.fromSecretary(savedSecretary);
        }
    }

    @Override
    public SecretaryDTO updateSecretary(SecretaryDTO secretaryDTO) {
        Secretary secretary = mapper.fromSecretaryDTO(secretaryDTO);
        Secretary savedSecretary = secretaryRepository.save(secretary);
        UserAccount userAccount = mapper.fromUserAccountDTO(secretaryDTO.getUserAccountDTO());
        userAccount.setUser(savedSecretary);
        userAccount.setRole("SECRETARY");
        savedSecretary.setUserAccount(userAccountRepository.save(userAccount));
        return mapper.fromSecretary(savedSecretary);
    }

    @Override
    public void deleteSecretary(Long secretaryId) {
        secretaryRepository.deleteById(secretaryId);
    }

    @Override
    public SecretaryDTO getSecretaryDTO(String username) throws UserNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) throw new UserNotFoundException("User Not Found");
        Secretary sec = secretaryRepository.findById(userAccount.getUser().getId()).orElse(null);
        if (sec == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            return mapper.fromSecretary(sec);
        }
    }
}
