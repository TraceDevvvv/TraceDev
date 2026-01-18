package com.example.smos.service;

import com.example.smos.model.Teaching;
import com.example.smos.repository.TeachingRepository;
import com.example.smos.exception.SMOSConnectionException;

import java.util.List;

/**
 * Service layer responsible for business logic related to archiving teachings.
 * It orchestrates operations using the TeachingRepository.
 * Corresponds to the 'TeachingArchiveService' class in the UML Class Diagram.
 */
public class TeachingArchiveService {

    private final TeachingRepository teachingRepository; // Dependency on TeachingRepository

    /**
     * Constructor for TeachingArchiveService, demonstrating dependency injection.
     * @param repo The TeachingRepository implementation to be used by this service.
     */
    public TeachingArchiveService(TeachingRepository repo) {
        this.teachingRepository = repo;
    }

    /**
     * Eliminates a teaching from the archive.
     * This method implements the core logic of the 'Eliminate Teaching from Archive' use case.
     * It handles the interaction with the repository and potential exceptions.
     * @param teachingId The unique identifier of the teaching to eliminate.
     * @return A list of updated teachings after the elimination, or null if an error occurred.
     * @throws SMOSConnectionException if a database connection issue occurs during the process.
     */
    public List<Teaching> eliminateTeaching(String teachingId) throws SMOSConnectionException {
        System.out.println("Service: eliminateTeaching(" + teachingId + ") - Orchestrating deletion.");
        try {
            // 1. Delete the teaching
            teachingRepository.delete(teachingId); // This call might throw SMOSConnectionException

            // 2. If deletion is successful, retrieve the updated list of teachings
            System.out.println("Service: Deletion successful. Now retrieving updated list.");
            List<Teaching> updatedTeachingList = teachingRepository.findAll(); // This call might throw SMOSConnectionException
            System.out.println("Service: Successfully retrieved updated teaching list.");
            return updatedTeachingList;

        } catch (SMOSConnectionException e) {
            // Log the exception and re-throw it to be handled by the caller (UserInterface)
            System.err.println("Service: Caught SMOSConnectionException: " + e.getMessage());
            throw e; // Re-throwing the exception as per sequence diagram alternative flow
        }
    }
}