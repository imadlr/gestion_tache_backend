package com.gt.gestion_taches.repositories;

import com.gt.gestion_taches.entities.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecretaryRepository extends JpaRepository<Secretary,Long> {
    List<Secretary> findByCniContains(String keyword);
}
