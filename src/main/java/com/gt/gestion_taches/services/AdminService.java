package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.AdminDTO;
import com.gt.gestion_taches.dtos.DivisionDTO;
import com.gt.gestion_taches.dtos.UserAccountDTO;
import com.gt.gestion_taches.exceptions.UserNameExistsException;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

import java.util.List;

public interface AdminService {

    List<AdminDTO> getByCniContains(String keyword);

    AdminDTO saveAdmin(AdminDTO adminDTO, UserAccountDTO userAccountDTO) throws UserNameExistsException;

    AdminDTO updateAdmin(AdminDTO adminDTO, UserAccountDTO userAccountDTO);

    void deleteAdmin(Long adminId);

    AdminDTO getAdminDTO(String username) throws UserNotFoundException;
}
