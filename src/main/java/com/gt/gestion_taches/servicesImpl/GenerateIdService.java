package com.gt.gestion_taches.servicesImpl;

import com.gt.gestion_taches.entities.Admin;
import com.gt.gestion_taches.entities.Division;
import com.gt.gestion_taches.entities.Responsible;
import com.gt.gestion_taches.entities.Secretary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

@Service
public class GenerateIdService {

    @PersistenceContext
    private EntityManager entityManager;

    Division division = new Division();
    Responsible responsible = new Responsible();
    Admin admin = new Admin();
    Secretary secretary = new Secretary();

    public Long getMaxIdAmongEntities(Class<?>... entityClasses) {

        long maxId = 0;

        for (Class<?> entityClass : entityClasses) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<?> root = query.from(entityClass);

            query.select(cb.max(root.get("id")));

            Long maxIdForEntity = entityManager.createQuery(query).getSingleResult();
            if (maxIdForEntity != null && maxIdForEntity > maxId) {
                maxId = maxIdForEntity;
            }
        }

        return maxId;
    }

    public Long getGeneratedId() {
        return getMaxIdAmongEntities(division.getClass(), responsible.getClass(), admin.getClass(), secretary.getClass()) + 1;
    }


 /*   public Long generateId(Class<?> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<?> root = query.from(entityClass);
        query.select(cb.count(root));

        Long count = entityManager.createQuery(query).getSingleResult();
        return count;
    }

    public Long getGeneratedId() {
        return generateId(division.getClass()) + generateId(responsible.getClass()) + generateId(admin.getClass()) + generateId(secretary.getClass()) + 1;
    }*/
}
