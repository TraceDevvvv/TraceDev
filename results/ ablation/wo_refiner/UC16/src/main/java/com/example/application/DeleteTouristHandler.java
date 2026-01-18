
package com.example.application;

import com.example.domain.Tourist;
import com.example.dto.CommandResult;

/**
 * Application Layer - Orchestrates the deletion use case.
 * Marked as reliable per diagram.
 */
public class DeleteTouristHandler {
    private ITouristRepository touristRepository;
    private IUnitOfWork unitOfWork;
    private INotificationService notificationService;

    public DeleteTouristHandler(ITouristRepository touristRepository,
                                IUnitOfWork unitOfWork,
                                INotificationService notificationService) {
        this.touristRepository = touristRepository;
        this.unitOfWork = unitOfWork;
        this.notificationService = notificationService;
    }

    /**
     * Handles the delete tourist command as per sequence diagram.
     * Implements main success flow, alternative flows.
     */
    public CommandResult handle(DeleteTouristCommand command) {
        // Step 6: Get tourist by ID
        Tourist tourist = touristRepository.getById(command.getTouristId());
        if (tourist == null) {
            // Alternative Flow: Tourist not found
            return new CommandResult(false, "Tourist not found", command.getTouristId());
        }

        // Step 7: Call domain delete
        tourist.delete();

        // Step 8: Repository delete
        touristRepository.delete(tourist);

        // Step 9: Commit transaction
        boolean commitSuccess = unitOfWork.commit();

        if (commitSuccess) {
            // Transaction successful
            // Step 10: Notify
            notificationService.notifyAccountDeleted(command.getTouristId());

            // Return success result
            return new CommandResult(true, "Tourist deleted successfully", command.getTouristId());
        } else {
            // Transaction failed
            unitOfWork.rollback();
            // The sequence diagram also includes a connection interruption scenario.
            // We'll treat any commit failure as potential connection issue.
            return new CommandResult(false, "Transaction failed. Connection may be interrupted.", command.getTouristId());
        }
    }
}
