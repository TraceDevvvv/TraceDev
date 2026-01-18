package com.example.justice.repository;

import com.example.justice.domain.Justice;

/**
 * Interface defining the contract for data access operations on Justice entities.
 * This is part of the Repository pattern.
 */
public interface JusticeRepository {

    /**
     * Finds a Justice record by its unique identifier.
     *
     * @param id The ID of the Justice to find.
     * @return The Justice object if found, otherwise null.
     */
    Justice findById(String id);

    /**
     * Saves a Justice record. If the record already exists (based on ID), it should be updated.
     * If it's a new record, it should be created.
     *
     * @param justice The Justice object to save.
     * @return The saved Justice object.
     */
    Justice save(Justice justice);
}