package com.gt.gestion_taches.repositories;

import com.gt.gestion_taches.entities.Secretary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaryRepository extends JpaRepository<Secretary,Long> {
    Page<Secretary> findByCniContains(String keyword, Pageable pageable);
}
