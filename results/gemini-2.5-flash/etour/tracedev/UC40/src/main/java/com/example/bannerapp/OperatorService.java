package com.example.bannerapp;

/**
 * Service layer for Operator-related business logic.
 * Added to satisfy recommendation REC-001.
 */
public class OperatorService {
    private IOperatorRepository operatorRepository;

    /**
     * Constructs an OperatorService with a given repository.
     * @param operatorRepository The repository to use for operator data access.
     */
    public OperatorService(IOperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    /**
     * Retrieves the Point of Rest ID associated with a given operator ID.
     * @param operatorId The unique identifier of the operator.
     * @return The pointOfRestId managed by the operator, or null if operator not found.
     */
    public String getPointOfRestIdForOperator(String operatorId) {
        System.out.println("[OperatorService] Getting PointOfRestId for operator: " + operatorId);
        Operator operator = operatorRepository.findById(operatorId);
        if (operator != null) {
            return operator.getPointOfRestId();
        }
        System.out.println("[OperatorService] Operator " + operatorId + " not found.");
        return null; // Or throw a specific exception if operator not found
    }
}