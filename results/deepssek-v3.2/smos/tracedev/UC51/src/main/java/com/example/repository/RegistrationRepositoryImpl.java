package com.example.repository;

import com.example.model.RegistrationRequest;
import com.example.model.RequestStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the registration repository.
 * Uses an in-memory map for simplicity.
 */
public class RegistrationRepositoryImpl implements RegistrationRepository {
    private Map<String, RegistrationRequest> storage = new HashMap<>();

    @Override
    public void save(RegistrationRequest request) {
        storage.put(request.getRequestId(), request);
    }

    @Override
    public RegistrationRequest findById(String requestId) {
        return storage.get(requestId);
    }

    @Override
    public void deleteById(String requestId) {
        storage.remove(requestId);
    }

    @Override
    public List<RegistrationRequest> findAllPending() {
        List<RegistrationRequest> pending = new ArrayList<>();
        for (RegistrationRequest request : storage.values()) {
            if (request.getStatus() == RequestStatus.PENDING) {
                pending.add(request);
            }
        }
        return pending;
    }
}