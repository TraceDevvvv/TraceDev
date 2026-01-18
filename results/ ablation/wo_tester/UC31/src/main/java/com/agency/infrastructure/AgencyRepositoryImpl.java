package com.agency.infrastructure;

import com.agency.domain.AgencyOperator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Concrete implementation of IAgencyRepository.
 * Simulates database operations and connection errors.
 */
public class AgencyRepositoryImpl implements IAgencyRepository {
    // Simulating an in‑memory database for demonstration
    private Map<String, AgencyOperator> database = new HashMap<>();
    private Random random = new Random();

    public AgencyRepositoryImpl() {
        // Seed with a sample operator for testing
        AgencyOperator sample = new AgencyOperator("user123", "loggedInUser", 
            "$2a$12$somehashedpassword", "operator@example.com");
        database.put("loggedInUser", sample);
    }

    @Override
    public boolean save(AgencyOperator operator) {
        database.put(operator.getUsername(), operator);
        System.out.println("Saved operator: " + operator.getUsername());
        return true;
    }

    @Override
    public Optional<AgencyOperator> findByUsername(String username) {
        AgencyOperator operator = database.get(username);
        return Optional.ofNullable(operator);
    }

    @Override
    public boolean saveWithFallback(AgencyOperator operator) {
        // Simulate random connection errors (Exit Condition 2)
        // 20% chance of connection loss for demonstration
        if (random.nextInt(100) < 20) {
            System.out.println("Simulated database connection lost.");
            return false;
        }
        return save(operator);
    }
}