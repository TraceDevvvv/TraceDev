package com.example;

import java.util.List;

/**
 * Service layer for Teaching operations.
 */
public class TeachingService {
    private TeachingRepository teachingRepository;

    public TeachingService(TeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }

    /**
     * Deletes a teaching by its ID.
     * Uses atomic deletion as per quality requirement.
     */
    public void deleteTeaching(String id) {
        teachingRepository.deleteAtomic(id);
    }

    /**
     * Retrieves all teachings.
     */
    public List<Teaching> getAllTeachings() {
        return teachingRepository.findAll();
    }
}