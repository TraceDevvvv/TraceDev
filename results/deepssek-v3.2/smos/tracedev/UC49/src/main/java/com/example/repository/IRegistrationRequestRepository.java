package com.example.repository;

import com.example.domain.RegistrationRequest;
import java.util.List;

/**
 * Repository interface for RegistrationRequest entities.
 */
public interface IRegistrationRequestRepository {
    List<RegistrationRequest> findAllPending();
}