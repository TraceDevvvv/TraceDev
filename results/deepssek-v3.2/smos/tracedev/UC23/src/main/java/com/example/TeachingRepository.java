package com.example;

import java.util.List;

/**
 * Repository interface for Teaching entities.
 */
public interface TeachingRepository {
    /**
     * Saves a teaching entity.
     * @param teaching the teaching to save
     * @return the saved teaching
     */
    Teaching save(Teaching teaching);

    /**
     * Retrieves all teaching entities.
     * @return a list of all teachings
     */
    List<Teaching> findAll();
}