package com.gt.gestion_taches.repositories;

import com.gt.gestion_taches.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByCniContains(String keyword);
}
