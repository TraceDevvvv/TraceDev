package com.example.adapter.out.persistence;

import com.example.application.port.out.ConventionRequestRepository;
import com.example.domain.ConventionRequest;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaConventionRequestRepository implements ConventionRequestRepository {
    private final EntityManager entityManager;

    public JpaConventionRequestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ConventionRequest save(ConventionRequest request) {
        entityManager.persist(request);
        return request;
    }

    @Override
    public Optional<ConventionRequest> findById(String requestId) {
        ConventionRequest request = entityManager.find(ConventionRequest.class, requestId);
        return Optional.ofNullable(request);
    }

    @Override
    public List<ConventionRequest> findByRestaurantId(String restaurantId) {
        return entityManager.createQuery(
            "SELECT r FROM ConventionRequest r WHERE r.restaurantId = :restaurantId",
            ConventionRequest.class
        ).setParameter("restaurantId", restaurantId).getResultList();
    }
}
