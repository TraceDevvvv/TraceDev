package com.example.etour.usecases;

/**
 * Input port for the Delete Refreshment Point use case.
 */
public interface DeleteRefreshmentPointInputPort {
    /**
     * Deletes a refreshment point by its ID.
     * @param id the refreshment point ID.
     * @return true if deletion was successful, false otherwise.
     */
    boolean delete(String id);
}