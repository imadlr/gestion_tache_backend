package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.AdminDTO;
import com.gt.gestion_taches.dtos.UserAccountDTO;
import com.gt.gestion_taches.entities.Admin;
import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.AdminRepository;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import com.gt.gestion_taches.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private UserAccountRepository userAccountRepository;
    private MapperServiceImpl mapper;
    private GenerateIdService generateIdService;

    @Override
    public List<AdminDTO> getByCniContains(String keyword) {
        return adminRepository.findByCniContains(keyword).stream().map(admin -> mapper.fromAdmin(admin)).toList();
    }

    @Override
    public AdminDTO saveAdmin(AdminDTO adminDTO, UserAccountDTO userAccountDTO) throws UserNameExistsException {
        UserAccount checkUser = userAccountRepository.findByUsername(userAccountDTO.getUsername());
        if (checkUser != null) {
            throw new UserNameExistsException("Utilisateur Déja Existe");
        } else {
            Admin admin = mapper.fromAdminDTO(adminDTO);
            admin.setId(generateIdService.getGeneratedId());
            Admin savedAdmin = adminRepository.save(admin);
            UserAccount userAccount = mapper.fromUserAccountDTO(userAccountDTO);
            userAccount.setUser(savedAdmin);
            userAccount.setRole("ADMIN");
            userAccountRepository.save(userAccount);
            return mapper.fromAdmin(savedAdmin);
        }
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO adminDTO, UserAccountDTO userAccountDTO) {
        Admin savedAdmin = adminRepository.save(mapper.fromAdminDTO(adminDTO));
        UserAccount userAccount = mapper.fromUserAccountDTO(userAccountDTO);
        userAccount.setUser(savedAdmin);
        userAccount.setRole("ADMIN");
        userAccountRepository.save(userAccount);
        return mapper.fromAdmin(savedAdmin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }
}
