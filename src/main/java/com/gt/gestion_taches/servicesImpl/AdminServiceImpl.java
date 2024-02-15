package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.dtos.AdminDTO;
import com.gt.gestion_taches.entities.Admin;
import com.gt.gestion_taches.entities.UserAccount;
import com.gt.gestion_taches.exceptions.UserNotFoundException;
import com.gt.gestion_taches.mappers.MapperServiceImpl;
import com.gt.gestion_taches.repositories.AdminRepository;
import com.gt.gestion_taches.repositories.UserAccountRepository;
import com.gt.gestion_taches.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private UserAccountRepository userAccountRepository;
    private MapperServiceImpl mapper;

    @Override
    public AdminDTO getAdminDTO(String username) throws UserNotFoundException {
        UserAccount account = userAccountRepository.findByUsername(username);
        if (account == null) throw new UserNotFoundException("Username not found");
        Admin admin = adminRepository.findById(account.getUser().getId()).orElse(null);
        if (admin == null) throw new UserNotFoundException("Admin not found");
        return mapper.fromAdmin(admin);
    }
}
