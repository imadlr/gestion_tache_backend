package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.entities.Secretary;
import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.SecretaryRepository;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import com.gt.gestion_taches.services.SecretaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public PageSecretaryDTO getByCniContains(String keyword, int page, int size) {
        Page<Secretary> secretaries = secretaryRepository.findByCniContains(keyword, PageRequest.of(page, size));
        List<SecretaryDTO> secretaryDTOS = secretaries.getContent().stream().map(sec -> mapper.fromSecretary(sec)).toList();
        PageSecretaryDTO pageSecretaryDTO = new PageSecretaryDTO();
        pageSecretaryDTO.setSecretaryDTOS(secretaryDTOS);
        pageSecretaryDTO.setPageSize(size);
        pageSecretaryDTO.setCurrentPage(page);
        pageSecretaryDTO.setTotalPages(secretaries.getTotalPages());
        return pageSecretaryDTO;
    }

    @Override
    public SecretaryDTO saveSecretary(SecretaryDTO secretaryDTO) throws UserNameExistsException {
        UserAccount userAccount = mapper.fromUserAccountDTO(secretaryDTO.getUserAccountDTO());
        UserAccount checkUser = userAccountRepository.findByUsername(userAccount.getUsername());
        if (checkUser != null) {
            throw new UserNameExistsException("Utilisateur Déja Existe");
        } else {
            Secretary secretary = new Secretary();
            secretary.setId(generateIdService.getGeneratedId());
            secretary.setFirstName(secretaryDTO.getFirstName());
            secretary.setLastName(secretaryDTO.getLastName());
            secretary.setCni(secretaryDTO.getCni());
            Secretary savedSecretary = secretaryRepository.save(secretary);
            userAccount.setUser(savedSecretary);
            userAccount.setRole("SECRETARY");
            savedSecretary.setUserAccount(userAccountRepository.save(userAccount));
            return mapper.fromSecretary(savedSecretary);
        }
    }

    @Override
    public SecretaryDTO updateSecretary(SecretaryDTO secretaryDTO) {
        Secretary secretary = new Secretary();
        secretary.setId(secretaryDTO.getId());
        secretary.setFirstName(secretaryDTO.getFirstName());
        secretary.setLastName(secretaryDTO.getLastName());
        secretary.setCni(secretaryDTO.getCni());
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
}
