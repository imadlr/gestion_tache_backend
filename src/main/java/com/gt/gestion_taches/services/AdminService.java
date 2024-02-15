package com.gt.gestion_taches.services;

import com.gt.gestion_taches.dtos.AdminDTO;
import com.gt.gestion_taches.exceptions.UserNotFoundException;

public interface AdminService {

    AdminDTO getAdminDTO(String username) throws UserNotFoundException;

}
