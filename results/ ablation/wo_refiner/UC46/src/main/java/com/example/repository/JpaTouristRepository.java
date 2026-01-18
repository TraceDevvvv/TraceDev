package com.example.repository;

import com.example.model.Tourist;
import jakarta.persistence.EntityManager;
import java.util.Optional;

/**
 * JPA implementation of TouristRepository.
 */
public class JpaTouristRepository implements TouristRepository {
    private EntityManager entityManager;

    public JpaTouristRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Tourist> findById(String id) {
        Tourist tourist = entityManager.find(Tourist.class, id);
        return Optional.ofNullable(tourist);
    }
}