package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.AdminDTO;
import com.gt.gestion_taches.dtos.PageAdminDTO;
import com.gt.gestion_taches.dtos.UserAccountDTO;
import com.gt.gestion_taches.exceptions.UserNameExistsException;

import java.util.List;

public interface AdminService {
    PageAdminDTO getAdmins(int page, int size);

    List<AdminDTO> getByCniContains(String keyword);

    AdminDTO saveAdmin(AdminDTO adminDTO, UserAccountDTO userAccountDTO) throws UserNameExistsException;

    AdminDTO updateAdmin(AdminDTO adminDTO, UserAccountDTO userAccountDTO);

    void deleteAdmin(Long adminId);
}
