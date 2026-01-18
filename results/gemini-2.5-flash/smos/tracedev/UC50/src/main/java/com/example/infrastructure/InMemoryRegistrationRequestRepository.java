package com.example.infrastructure;

import com.example.domain.RegistrationRequest;
import com.example.domain.RegistrationStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of IRegistrationRequestRepository for demonstration purposes.
 * Stores RegistrationRequest objects in a HashMap.
 * Includes a configurable flag to simulate SMOSConnectionException.
 */
public class InMemoryRegistrationRequestRepository implements IRegistrationRequestRepository {
    private final Map<String, RegistrationRequest> requests = new HashMap<>();
    private boolean simulateSMOSConnectionFailure = false; // Default: no failure

    /**
     * Sets whether to simulate a SMOS connection failure during the update operation.
     * @param simulateFailure true to simulate failure, false otherwise.
     */
    public void setSimulateSMOSConnectionFailure(boolean simulateFailure) {
        this.simulateSMOSConnectionFailure = simulateFailure;
        System.out.println("[Repo] SMOS Connection Failure Simulation set to: " + simulateFailure);
    }

    @Override
    public RegistrationRequest findById(String requestId) {
        System.out.println("[Repo] Finding RegistrationRequest by ID: " + requestId);
        return requests.get(requestId);
    }

    @Override
    public void update(RegistrationRequest request) throws SMOSConnectionException {
        System.out.println("[Repo] Updating RegistrationRequest: " + request.getId());
        if (simulateSMOSConnectionFailure) {
            // Simulate a connection failure during the update operation
            throw new SMOSConnectionException("Simulated SMOS connection interruption during update for request ID: " + request.getId());
        }
        if (requests.containsKey(request.getId())) {
            requests.put(request.getId(), request); // Replace with the updated object
        } else {
            // In a real scenario, this might be an error or an insert operation
            System.err.println("[Repo] Warning: Attempted to update non-existent RegistrationRequest with ID: " + request.getId());
            requests.put(request.getId(), request); // Add it anyway for in-memory if not found, but log warning
        }
    }

    @Override
    public List<RegistrationRequest> findAllPending() {
        System.out.println("[Repo] Finding all PENDING RegistrationRequests.");
        return requests.values().stream()
                .filter(request -> request.getStatus() == RegistrationStatus.PENDING)
                .collect(Collectors.toList());
    }

    @Override
    public void save(RegistrationRequest request) {
        System.out.println("[Repo] Saving new RegistrationRequest: " + request.getId());
        requests.put(request.getId(), request);
    }
}