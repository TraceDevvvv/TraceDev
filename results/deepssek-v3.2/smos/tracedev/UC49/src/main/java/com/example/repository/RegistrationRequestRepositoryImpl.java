package com.example.repository;

import com.example.domain.RegistrationRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of IRegistrationRequestRepository.
 */
public class RegistrationRequestRepositoryImpl implements IRegistrationRequestRepository {
    @Override
    public List<RegistrationRequest> findAllPending() {
        // Simulating database fetch - returning dummy data for demonstration
        List<RegistrationRequest> requests = new ArrayList<>();
        requests.add(new RegistrationRequest(1L, "S001", "Alice Smith", new Date(), "PENDING"));
        requests.add(new RegistrationRequest(2L, "S002", "Bob Johnson", new Date(), "PENDING"));
        return requests;
    }
}