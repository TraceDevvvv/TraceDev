package com.example.convention.service;

import com.example.convention.dto.ConventionDTO;
import com.example.convention.model.Convention;
import com.example.convention.model.RefreshmentPoint;
import com.example.convention.repository.ConventionRepository;
import com.example.convention.repository.RefreshmentPointRepository;

/**
 * Service class responsible for handling convention activation logic.
 * This service orchestrates interactions between repositories and external serv.
 */
public class ConventionActivationService {
    private ConventionRepository conventionRepository;
    private ETOURService etourService;
    private RefreshmentPointRepository refreshmentPointRepository;

    /**
     * Constructor for ConventionActivationService.
     * Dependencies are injected via the constructor.
     *
     * @param conventionRepository The repository for Convention entities.
     * @param etourService The external ETOUR service.
     * @param refreshmentPointRepository The repository for RefreshmentPoint entities.
     */
    public ConventionActivationService(ConventionRepository conventionRepository,
                                       ETOURService etourService,
                                       RefreshmentPointRepository refreshmentPointRepository) {
        this.conventionRepository = conventionRepository;
        this.etourService = etourService;
        this.refreshmentPointRepository = refreshmentPointRepository;
    }

    /**
     * Retrieves detailed information about a convention.
     * [R5] Method `getConventionDetails` implemented to satisfy requirement R5.
     *
     * @param conventionId The ID of the convention to retrieve details for.
     * @return A ConventionDTO containing consolidated details.
     */
    public ConventionDTO getConventionDetails(String conventionId) {
        System.out.println("Service: getConventionDetails(" + conventionId + ")");
        // Service->ConvRepo: findById(conventionId)
        Convention foundConvention = conventionRepository.findById(conventionId);

        if (foundConvention == null) {
            System.out.println("Service: Convention " + conventionId + " not found.");
            return null; // Or throw an exception
        }

        // Service->RPRepo: findById(foundConvention.getRequestedById())
        RefreshmentPoint refreshmentPoint = refreshmentPointRepository.findById(foundConvention.getRequestedById());

        String refreshmentPointName = (refreshmentPoint != null) ? refreshmentPoint.getName() : "N/A";

        // Service->Service: createConventionDTO(foundConvention, refreshmentPoint)
        // This is done inline for simplicity
        ConventionDTO dto = new ConventionDTO(
            foundConvention.getId(),
            refreshmentPointName,
            foundConvention.getStatus(),
            foundConvention.getDetails()
        );
        System.out.println("Service: Returning DTO for Convention " + conventionId);
        return dto;
    }

    /**
     * Activates a convention based on its ID.
     * [R1, R9, R12] Method `activateConvention` implemented to satisfy requirements R1, R9, R12.
     * It checks if the associated refreshment point is designated and notifies ETOUR.
     *
     * @param conventionId The ID of the convention to activate.
     * @return True if activation was successful (even with ETOUR notification warning), false otherwise.
     */
    public boolean activateConvention(String conventionId) {
        System.out.println("Service: activateConvention(" + conventionId + ")");

        // Service->ConvRepo: findById(conventionId)
        Convention conventionToActivate = conventionRepository.findById(conventionId);

        if (conventionToActivate == null) {
            System.out.println("Service: Convention " + conventionId + " not found for activation.");
            return false;
        }
        
        if ("ACTIVE".equals(conventionToActivate.getStatus())) {
            System.out.println("Service: Convention " + conventionId + " is already active.");
            return true; // Already active, consider it a success
        }

        // Service->RPRepo: findById(conventionToActivate.getRequestedById())
        RefreshmentPoint refreshmentPoint = refreshmentPointRepository.findById(conventionToActivate.getRequestedById());

        if (refreshmentPoint == null) {
            System.out.println("Service: Associated RefreshmentPoint " + conventionToActivate.getRequestedById() + " not found.");
            return false;
        }

        // Alt: Entry Condition: Agency IS designated point of rest (through RefreshmentPoint)
        // Service->RP: isDesignatedPointOfRest()
        if (refreshmentPoint.isDesignatedPointOfRest()) {
            System.out.println("Service: Refreshment point " + refreshmentPoint.getName() + " IS designated. Proceeding with activation.");

            // Service->ConventionEntity: activate()
            conventionToActivate.activate(); // Changes status to "ACTIVE"

            // Service->ConvRepo: save(conventionToActivate)
            conventionRepository.save(conventionToActivate);
            // [R9, R12] System processes request, ensures integrity (simulated by save).

            // Opt: ETOUR Notification (Exit Condition: Interruption of ETOUR connection)
            // [R10, R11] ETOUR notification part to satisfy requirements R10, R11.
            System.out.println("Service: Attempting to send ETOUR activation notification for " + conventionToActivate.getId());
            boolean etourNotificationSuccess = etourService.sendActivationNotification(conventionToActivate.getId());

            if (etourNotificationSuccess) {
                System.out.println("Service: ETOUR notification successful for convention " + conventionToActivate.getId());
                return true; // Activation successful and ETOUR notified
            } else {
                System.out.println("Service: ETOUR notification FAILED for convention " + conventionToActivate.getId() + ". Logging for retry/manual intervention.");
                // Log ETOUR notification failure for retry/manual intervention.
                // Update convention details to reflect ETOUR notification failure for UI to pick up.
                // This makes the existing logic in AgencyOperatorController.confirmActivation() valid.
                conventionToActivate.setDetails(conventionToActivate.getDetails() + " [ETOUR notification failed]");
                conventionRepository.save(conventionToActivate); // Save the updated details with warning
                return true; // Activation was successful, but ETOUR notification failed (return true with warning context)
            }
        } else {
            // Else Activation Failed: Refreshment Point NOT Designated
            System.out.println("Service: Refreshment point " + refreshmentPoint.getName() + " IS NOT designated. Activation denied.");
            return false; // Activation failed
        }
    }
}