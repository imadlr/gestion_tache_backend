package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.entities.Division;
import com.gt.gestion_taches.entities.Responsible;
import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.ResponsibleRepository;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import com.gt.gestion_taches.services.ResponsibleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class ResponsibleServiceImpl implements ResponsibleService {
    private ResponsibleRepository responsibleRepository;
    private UserAccountRepository userAccountRepository;
    private MapperServiceImpl mapper;
    private GenerateIdService generateIdService;

    @Override
    public ResponsibleDTO getResponsibleDTO(String username) throws UserNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) throw new UserNotFoundException("User Not Found");
        Responsible resp = responsibleRepository.findById(userAccount.getUser().getId()).orElse(null);
        if (resp == null) {
            throw new UserNotFoundException("User Not Found");
        } else {
            return mapper.fromResponsible(resp);
        }
    }

    @Override
    public List<ResponsibleDTO> getByCniContains(String keyword) {
        List<Responsible> responsibles = responsibleRepository.findByCniContains(keyword);
        return responsibles.stream().map(responsible -> mapper.fromResponsible(responsible)).toList();
    }

    @Override
    public ResponsibleDTO saveResponsible(ResponsibleDTO responsibleDTO) throws UserNameExistsException {
        UserAccount userAccount = mapper.fromUserAccountDTO(responsibleDTO.getUserAccountDTO());
        UserAccount checkUser = userAccountRepository.findByUsername(userAccount.getUsername());
        if (checkUser != null) {
            throw new UserNameExistsException("Utilisateur Déja Existe");
        } else {
            Responsible responsible = new Responsible();
            responsible.setId(generateIdService.getGeneratedId());
            responsible.setFirstName(responsibleDTO.getFirstName());
            responsible.setLastName(responsibleDTO.getLastName());
            responsible.setCni(responsibleDTO.getCni());
            Responsible savedResponsible = responsibleRepository.save(responsible);
            userAccount.setUser(savedResponsible);
            userAccount.setRole("RESPONSIBLE");
            savedResponsible.setUserAccount(userAccountRepository.save(userAccount));
            return mapper.fromResponsible(savedResponsible);
        }
    }

    @Override
    public ResponsibleDTO updateResponsible(ResponsibleDTO responsibleDTO) {
        Responsible responsible = new Responsible();
        responsible.setId(responsibleDTO.getId());
        responsible.setFirstName(responsibleDTO.getFirstName());
        responsible.setLastName(responsibleDTO.getLastName());
        responsible.setCni(responsibleDTO.getCni());
        Responsible savedResponsible = responsibleRepository.save(responsible);
        UserAccount userAccount = mapper.fromUserAccountDTO(responsibleDTO.getUserAccountDTO());
        userAccount.setUser(savedResponsible);
        userAccount.setRole("RESPONSIBLE");
        savedResponsible.setUserAccount(userAccountRepository.save(userAccount));
        return mapper.fromResponsible(savedResponsible);
    }

    @Override
    public void deleteResponsible(Long responsibleId) {
        responsibleRepository.deleteById(responsibleId);
    }

    @Override
    public Responsible getResponsible(Long responsibleId) throws UserNotFoundException {
        Responsible responsible = responsibleRepository.findById(responsibleId).orElse(null);
        if (responsible == null) {
            throw new UserNotFoundException("Responsable N'est Pas Trouvé");
        } else {
            return responsible;
        }
    }
}
