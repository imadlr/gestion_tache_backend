package com.gt.gestion_taches.repositories;

import com.gt.gestion_taches.entities.Responsible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsibleRepository extends JpaRepository<Responsible,Long> {
    Page<Responsible> findByCniContains(String keyword, Pageable pageable);
}
