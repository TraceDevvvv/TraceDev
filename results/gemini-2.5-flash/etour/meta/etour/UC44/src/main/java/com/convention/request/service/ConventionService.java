package com.convention.request.service;

import com.convention.request.exception.AgencyCommunicationException;
import com.convention.request.exception.InvalidConventionDataException;
import com.convention.request.model.AgencyResponseDTO;
import com.convention.request.model.Convention;
import com.convention.request.model.ConventionRequestDTO;
import com.convention.request.model.ConventionStatus;
import com.convention.request.repository.ConventionRepository;
import com.convention.request.util.ConventionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class responsible for handling the business logic related to Convention requests.
 * This includes creating, validating, retrieving, updating status, and sending conventions to the agency.
 */
@Service
@Slf4j
public class ConventionService {

    private final ConventionRepository conventionRepository;
    private final AgencyService agencyService;
    private final ConventionValidator conventionValidator;

    /**
     * Constructor for ConventionService, injecting required dependencies.
     *
     * @param conventionRepository The repository for Convention entities.
     * @param agencyService The service for communicating with the external agency.
     * @param conventionValidator The utility for validating ConventionRequestDTOs.
     */
    @Autowired
    public ConventionService(ConventionRepository conventionRepository, AgencyService agencyService, ConventionValidator conventionValidator) {
        this.conventionRepository = conventionRepository;
        this.agencyService = agencyService;
        this.conventionValidator = conventionValidator;
    }

    /**
     * Creates a new convention request.
     * Performs validation on the input DTO, saves the convention with PENDING status,
     * and returns the created Convention object.
     *
     * @param request The ConventionRequestDTO containing the data for the new convention.
     * @return The newly created Convention object.
     * @throws InvalidConventionDataException if the provided convention data is invalid.
     */
    @Transactional
    public Convention createConvention(ConventionRequestDTO request) {
        log.info("Attempting to create a new convention request for agency: {}", request.getAgencyName());

        // 1. Validate the incoming DTO
        // JSR 380 annotations are handled by @Valid in controller, but custom logic is here.
        conventionValidator.validate(request);

        // Create a new Convention entity from the DTO
        Convention convention = new Convention();
        // conventionId is generated @PrePersist
        convention.setAgencyName(request.getAgencyName());
        convention.setConventionType(request.getConventionType());
        convention.setStartDate(request.getStartDate());
        convention.setEndDate(request.getEndDate());
        convention.setDescription(request.getDescription());
        convention.setContactPerson(request.getContactPerson());
        convention.setContactEmail(request.getContactEmail());
        convention.setRequiredDocuments(request.getRequiredDocuments());
        convention.setStatus(ConventionStatus.PENDING); // Initial status

        // Save the convention to the database
        Convention savedConvention = conventionRepository.save(convention);
        log.info("Convention created successfully with ID: {}", savedConvention.getConventionId());
        return savedConvention;
    }

    /**
     * Retrieves a convention by its unique identifier.
     *
     * @param conventionId The ID of the convention to retrieve.
     * @return The Convention object if found.
     * @throws InvalidConventionDataException if no convention is found with the given ID.
     */
    @Transactional(readOnly = true)
    public Convention getConventionById(String conventionId) {
        log.debug("Fetching convention with ID: {}", conventionId);
        return conventionRepository.findById(conventionId)
                .orElseThrow(() -> new InvalidConventionDataException("Convention not found with ID: " + conventionId));
    }

    /**
     * Updates the status of an existing convention.
     *
     * @param conventionId The ID of the convention to update.
     * @param status The new status to set.
     * @return The updated Convention object.
     * @throws InvalidConventionDataException if no convention is found with the given ID.
     */
    @Transactional
    public Convention updateConventionStatus(String conventionId, ConventionStatus status) {
        log.info("Updating status for convention ID {} to {}", conventionId, status);
        int updatedRows = conventionRepository.updateStatus(conventionId, status);
        if (updatedRows == 0) {
            throw new InvalidConventionDataException("Convention not found or status not updated for ID: " + conventionId);
        }
        return getConventionById(conventionId); // Retrieve the updated convention
    }

    /**
     * Sends a previously created convention to the external agency for processing.
     * Updates the convention status to SENT_TO_AGENCY upon successful submission.
     *
     * @param convention The Convention object to send.
     * @return The AgencyResponseDTO received from the agency.
     * @throws AgencyCommunicationException if there's an issue communicating with the agency.
     * @throws InvalidConventionDataException if the convention is not in a state to be sent (e.g., already sent or cancelled).
     */
    @Transactional
    public AgencyResponseDTO sendConventionToAgency(Convention convention) {
        log.info("Sending convention ID {} to external agency.", convention.getConventionId());

        // Ensure the convention is in a state that allows it to be sent
        if (convention.getStatus() != ConventionStatus.PENDING) {
            throw new InvalidConventionDataException("Convention ID " + convention.getConventionId() +
                    " cannot be sent to agency. Current status: " + convention.getStatus());
        }

        AgencyResponseDTO agencyResponse;
        try {
            agencyResponse = agencyService.sendRequest(convention);
            // Update internal status to SENT_TO_AGENCY after successful submission
            updateConventionStatus(convention.getConventionId(), ConventionStatus.SENT_TO_AGENCY);
            agencyService.handleAgencyResponse(agencyResponse); // Process agency's response
        } catch (AgencyCommunicationException e) {
            // If communication fails, update status to ERROR and re-throw
            updateConventionStatus(convention.getConventionId(), ConventionStatus.ERROR);
            log.error("Failed to send convention ID {} to agency. Status set to ERROR.", convention.getConventionId(), e);
            throw e;
        } catch (Exception e) {
            // Catch any other unexpected errors during the send process
            updateConventionStatus(convention.getConventionId(), ConventionStatus.ERROR);
            log.error("An unexpected error occurred while sending convention ID {} to agency. Status set to ERROR.", convention.getConventionId(), e);
            throw new AgencyCommunicationException("An unexpected error occurred while sending convention to agency.", e);
        }

        log.info("Convention ID {} successfully sent to agency. Agency response status: {}",
                convention.getConventionId(), agencyResponse.getStatus());
        return agencyResponse;
    }

    /**
     * Cancels an existing convention request.
     * Updates the convention status to CANCELLED.
     *
     * @param conventionId The ID of the convention to cancel.
     * @return The cancelled Convention object.
     * @throws InvalidConventionDataException if no convention is found with the given ID or it's already in a final state.
     */
    @Transactional
    public Convention cancelConvention(String conventionId) {
        log.info("Attempting to cancel convention with ID: {}", conventionId);
        Convention convention = getConventionById(conventionId);

        // Only allow cancellation if the convention is in a cancellable state
        if (convention.getStatus() == ConventionStatus.APPROVED ||
            convention.getStatus() == ConventionStatus.REJECTED ||
            convention.getStatus() == ConventionStatus.CANCELLED) {
            throw new InvalidConventionDataException("Convention ID " + conventionId +
                    " cannot be cancelled. Current status: " + convention.getStatus());
        }

        return updateConventionStatus(conventionId, ConventionStatus.CANCELLED);
    }
}