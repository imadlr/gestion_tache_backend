package com.gt.gestion_taches.repositories;
import com.gt.gestion_taches.entities.Division;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionRepository extends JpaRepository<Division,Long> {
    List<Division> findByCniContains(String keyword);
}
