package com.example.infrastructure.persistence;

import com.example.domain.RefreshmentPoint;
import com.example.domain.repository.RefreshmentPointRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * JPA implementation of RefreshmentPointRepository.
 * Implements REQ-006 and REQ-014 performance requirement.
 */

public class JpaRefreshmentPointRepository implements RefreshmentPointRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<RefreshmentPoint> searchByName(String name) {
        TypedQuery<RefreshmentPoint> query = entityManager.createQuery(
            "SELECT rp FROM RefreshmentPoint rp WHERE LOWER(rp.name) LIKE LOWER(CONCAT('%', :name, '%'))", 
            RefreshmentPoint.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}