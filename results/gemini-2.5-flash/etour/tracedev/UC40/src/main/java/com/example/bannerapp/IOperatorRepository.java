package com.example.bannerapp;

/**
 * Interface defining operations for managing Operator data persistence.
 */
public interface IOperatorRepository {
    /**
     * Finds an operator by their unique identifier.
     * @param operatorId The ID of the operator to find.
     * @return The Operator object if found, null otherwise.
     */
    Operator findById(String operatorId);
}