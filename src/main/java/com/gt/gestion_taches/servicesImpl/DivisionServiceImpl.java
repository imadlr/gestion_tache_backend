package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.*;
import com.gt.gestion_taches.entities.Division;
import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.DivisionRepository;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import com.gt.gestion_taches.services.DivisionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
@Transactional
@Service
public class DivisionServiceImpl implements DivisionService {
    private MapperServiceImpl mapper;
    private DivisionRepository divisionRepository;
    private UserAccountRepository userAccountRepository;
    private GenerateIdService generateIdService;

    @Override
    public List<DivisionDTO> getByCniContains(String keyword) {
        List<Division> divisions = divisionRepository.findByCniContains(keyword);
        return divisions.stream().map(div -> mapper.fromDivision(div)).toList();
    }

    @Override
    public Division getDivision(Long divisionId) throws UserNotFoundException {
        Division division = divisionRepository.findById(divisionId).orElse(null);
        if (division == null) {
            throw new UserNotFoundException("Division N'Est Pas Trouvé");
        } else {
            return division;
        }
    }

    @Override
    public DivisionDTO getDivisionDTO(String username) throws UserNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null) throw new UserNotFoundException("User Not Found");
        Division division = divisionRepository.findById(userAccount.getUser().getId()).orElse(null);
        if (division == null) {
            throw new UserNotFoundException("Division N'Est Pas Trouvé");
        } else {
            return mapper.fromDivision(division);
        }
    }

    @Override
    public DivisionDTO saveDivision(DivisionDTO divisionDTO) throws UserNameExistsException {
        UserAccount userAccount = mapper.fromUserAccountDTO(divisionDTO.getUserAccountDTO());
        UserAccount checkUser = userAccountRepository.findByUsername(userAccount.getUsername());
        if (checkUser != null) {
            throw new UserNameExistsException("Utilisateur Déja Existe");
        } else {
            Division division = new Division();
            division.setId(generateIdService.getGeneratedId());
            division.setNameDivision(divisionDTO.getNameDivision());
            division.setFirstName(divisionDTO.getFirstName());
            division.setLastName(divisionDTO.getLastName());
            division.setCni(divisionDTO.getCni());
            Division savedDivision = divisionRepository.save(division);
            userAccount.setUser(savedDivision);
            userAccount.setRole("DIVISION");
            savedDivision.setUserAccount(userAccountRepository.save(userAccount));
            return mapper.fromDivision(savedDivision);
        }
    }

    @Override
    public DivisionDTO updateDivision(DivisionDTO divisionDTO) {
        Division division = new Division();
        division.setId(divisionDTO.getId());
        division.setNameDivision(divisionDTO.getNameDivision());
        division.setFirstName(divisionDTO.getFirstName());
        division.setLastName(divisionDTO.getLastName());
        division.setCni(divisionDTO.getCni());
        Division savedDivision = divisionRepository.save(division);
        UserAccount userAccount = mapper.fromUserAccountDTO(divisionDTO.getUserAccountDTO());
        userAccount.setUser(savedDivision);
        userAccount.setRole("DIVISION");
        savedDivision.setUserAccount(userAccountRepository.save(userAccount));
        return mapper.fromDivision(savedDivision);
    }

    @Override
    public void deleteDivision(Long divisionId) {
        divisionRepository.deleteById(divisionId);
    }

    @Override
    public List<DivisionDTO> getDivisions() {
        return divisionRepository.findAll().stream().map(division -> mapper.fromDivision(division)).toList();
    }
}
