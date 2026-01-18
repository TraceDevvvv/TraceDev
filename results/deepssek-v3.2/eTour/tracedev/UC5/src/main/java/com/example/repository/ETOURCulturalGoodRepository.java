package com.example.repository;

import com.example.exception.ConnectionException;
import com.example.model.CulturalGood;

/**
 * Concrete implementation of CulturalGoodRepository.
 * Simulates a repository that might throw ConnectionException.
 */
public class ETOURCulturalGoodRepository implements CulturalGoodRepository {
    // Simulated data store; in reality, this would connect to a database or external service.
    private CulturalGood[] simulatedData = {
        new CulturalGood("1", "Ancient Vase", "A beautiful ancient vase from Roman times.", "Rome", "Roman"),
        new CulturalGood("2", "Medieval Sword", "A knight's sword from the 12th century.", "London", "Medieval")
    };

    @Override
    public CulturalGood findById(String id) throws ConnectionException {
        // Simulate a connection error randomly for demonstration.
        double random = Math.random();
        if (random < 0.3) { // 30% chance of connection error
            // Step 5 in error path: connectionError() - here we throw ConnectionException
            throw handleConnectionError();
        }

        // Normal flow: find the cultural good by ID.
        for (CulturalGood good : simulatedData) {
            if (good.getId().equals(id)) {
                return good;
            }
        }
        // If not found, assume it's a connection error (simplified for demonstration).
        throw new ConnectionException("Cultural good not found with ID: " + id);
    }

    /**
     * Creates a ConnectionException as per class diagram.
     * Used in the error path of the sequence diagram.
     * @return a ConnectionException with a descriptive message
     */
    public ConnectionException handleConnectionError() {
        return new ConnectionException("Connection to ETOUR database interrupted.");
    }
}