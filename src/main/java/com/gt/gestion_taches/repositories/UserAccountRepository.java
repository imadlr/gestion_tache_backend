package com.gt.gestion_taches.repositories;

import com.gt.gestion_taches.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    UserAccount findByUsername(String username);
}
