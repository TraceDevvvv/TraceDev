package com.example.application.port.out;

import com.example.domain.ConventionRequest;
import java.util.List;
import java.util.Optional;

/**
 * Output port for persisting ConventionRequest entities.
 */
public interface ConventionRequestRepository {
    ConventionRequest save(ConventionRequest request);
    Optional<ConventionRequest> findById(String requestId);
    List<ConventionRequest> findByRestaurantId(String restaurantId);
}