package com.example.bannerapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IOperatorRepository.
 * This class simulates database interactions for operators.
 */
public class OperatorRepositoryImpl implements IOperatorRepository {
    // In-memory data store for simulation
    private Map<String, Operator> operatorStore = new HashMap<>();

    /**
     * Constructor to initialize with some dummy data.
     */
    public OperatorRepositoryImpl() {
        operatorStore.put("OP001", new Operator("OP001", "POR001")); // Operator OP001 manages POR001
        operatorStore.put("OP002", new Operator("OP002", "POR002")); // Operator OP002 manages POR002
    }

    @Override
    public Operator findById(String operatorId) {
        System.out.println("[OperatorRepository] Finding operator with ID: " + operatorId);
        // Simulate a small delay
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return operatorStore.get(operatorId);
    }
}