package com.example.repository;

import com.example.model.RegistrationRequest;
import java.util.List;

/**
 * Interface for registration repository.
 * Implements Repository Pattern for abstracted data access.
 */
public interface RegistrationRepository {
    void save(RegistrationRequest request);
    RegistrationRequest findById(String requestId);
    void deleteById(String requestId);
    List<RegistrationRequest> findAllPending();
}