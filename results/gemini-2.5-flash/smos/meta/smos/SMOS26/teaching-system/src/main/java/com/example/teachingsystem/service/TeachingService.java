package com.example.teachingsystem.service;

import com.example.teachingsystem.exception.TeachingNotFoundException;
import com.example.teachingsystem.integration.smos.SMOSIntegrationService;
import com.example.teachingsystem.model.Teaching;
import com.example.teachingsystem.repository.TeachingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for managing Teaching entities and handling business logic.
 * This class orchestrates operations related to teachings, including deletion
 * and interaction with the SMOS server.
 */
@Service // Marks this class as a Spring service component
public class TeachingService {

    private final TeachingRepository teachingRepository;
    private final SMOSIntegrationService smosIntegrationService;

    /**
     * Constructor for TeachingService, injecting dependencies.
     * Spring's dependency injection automatically provides instances of
     * TeachingRepository and SMOSIntegrationService.
     *
     * @param teachingRepository The repository for Teaching entities.
     * @param smosIntegrationService The service for interacting with the SMOS server.
     */
    public TeachingService(TeachingRepository teachingRepository, SMOSIntegrationService smosIntegrationService) {
        this.teachingRepository = teachingRepository;
        this.smosIntegrationService = smosIntegrationService;
    }

    /**
     * Deletes a teaching from the archive based on its ID.
     * This method also handles the postcondition of interrupting the SMOS server connection.
     *
     * @param teachingId The unique identifier of the teaching to be deleted.
     * @throws TeachingNotFoundException if no teaching with the given ID is found.
     */
    @Transactional // Ensures that the entire method runs within a single database transaction
    public void deleteTeaching(Long teachingId) {
        // 1. Find the teaching by ID.
        // If the teaching is not found, throw a TeachingNotFoundException.
        Teaching teaching = teachingRepository.findById(teachingId)
                .orElseThrow(() -> new TeachingNotFoundException("Teaching with ID " + teachingId + " not found."));

        // 2. Eliminate teaching from the archive (Event 1).
        teachingRepository.delete(teaching);

        // 3. Interrupt connection to the SMOS server (Postcondition).
        // The SMOSIntegrationService handles the actual disconnection logic.
        smosIntegrationService.disconnect();
    }

    /**
     * Retrieves a list of all teachings currently in the archive.
     * This method is used to display the updated list of teachings after a deletion.
     *
     * @return A list of all Teaching entities.
     */
    public List<Teaching> getAllTeachings() {
        // 2. Displays the list of updated teachings (Event 2).
        return teachingRepository.findAll();
    }
}