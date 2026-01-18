package com.example.service;

import com.example.dto.ConventionRequestDTO;
import com.example.dto.ConventionResponseDTO;
import com.example.repository.ConventionRepository;

/**
 * Service class for processing convention requests.
 * Corresponds to the ConventionService class in the UML diagram.
 */
public class ConventionService {
    private ConventionRepository repository;

    /**
     * Constructor with repository dependency.
     * @param repository The convention repository.
     */
    public ConventionService(ConventionRepository repository) {
        this.repository = repository;
    }

    /**
     * Processes a convention request.
     * @param requestDTO The request DTO.
     * @return The response DTO.
     */
    public ConventionResponseDTO processConventionRequest(ConventionRequestDTO requestDTO) {
        System.out.println("Processing convention request...");
        // Save request to repository (step 9)
        boolean saveSuccess = repository.saveRequest(requestDTO);
        if (!saveSuccess) {
            ConventionResponseDTO response = new ConventionResponseDTO();
            response.setSuccess(false);
            response.setMessage("Failed to save request.");
            return response;
        }

        // Send request to agency (step 9)
        boolean sendSuccess = sendRequestToAgency(requestDTO);
        ConventionResponseDTO response = new ConventionResponseDTO();
        if (sendSuccess) {
            response.setSuccess(true);
            response.setMessage("Convention request sent successfully.");
            response.setRequestId("REQ-" + System.currentTimeMillis());
        } else {
            response.setSuccess(false);
            response.setMessage("Failed to send request to agency.");
        }
        return response;
    }

    /**
     * Sends the request to the agency (simulated ETOUR Server).
     * @param requestDTO The request DTO.
     * @return true if successful, false otherwise.
     */
    public boolean sendRequestToAgency(ConventionRequestDTO requestDTO) {
        System.out.println("Sending request to agency (ETOUR Server)...");
        // Simulate network connection and possible interruption
        try {
            // Simulate network delay
            Thread.sleep(500);
            // Simulate random connection failure (20% chance)
            if (Math.random() < 0.2) {
                throw new RuntimeException("Connection Lost");
            }
            System.out.println("Request received by agency.");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted while sending request.");
            return false;
        } catch (RuntimeException e) {
            System.out.println("Connection interrupted: " + e.getMessage());
            return false;
        }
    }
}