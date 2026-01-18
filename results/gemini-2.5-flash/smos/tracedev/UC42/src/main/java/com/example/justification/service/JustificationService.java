package com.example.justification.service;

import com.example.justification.domain.Justification;
import com.example.justification.infrastructure.IUnitOfWork;
import com.example.justification.repository.IJustificationRepository;

import java.util.Objects;

/**
 * Application service for managing Justifications.
 * This class orchestrates business logic and interacts with repositories and the unit of work.
 * Corresponds to the JustificationService class in the Class Diagram and the sequence diagram participant.
 */
public class JustificationService {
    private final IJustificationRepository _justificationRepository;
    private final IUnitOfWork _unitOfWork;

    /**
     * Constructs a JustificationService with its required dependencies.
     *
     * @param justificationRepository The repository for Justification entities.
     * @param unitOfWork The Unit of Work to manage transactions.
     */
    public JustificationService(IJustificationRepository justificationRepository, IUnitOfWork unitOfWork) {
        this._justificationRepository = Objects.requireNonNull(justificationRepository, "IJustificationRepository cannot be null");
        this._unitOfWork = Objects.requireNonNull(unitOfWork, "IUnitOfWork cannot be null");
    }

    /**
     * Handles the process of eliminating a justification.
     * This method implements the core logic described in the "Eliminate Justification" sequence diagram.
     *
     * @param justificationId The ID of the justification to be eliminated.
     * @throws RuntimeException if the justification is not found or a persistence error occurs.
     */
    public void eliminateJustification(String justificationId) {
        System.out.println("JustificationService: eliminateJustification(" + justificationId + ") started.");
        try {
            // Step 1: Begin transaction to ensure atomicity.
            // Guarantees transactional atomicity.
            _unitOfWork.beginTransaction();

            // Step 2: Retrieve the justification by its ID.
            Justification existingJustification = _justificationRepository.getById(justificationId);

            // Alt: Justification Found
            if (existingJustification != null) {
                System.out.println("JustificationService: Justification with ID " + justificationId + " found.");

                // Step 3: Mark the justification for deletion (internal aggregate status update).
                // Updates internal status within the aggregate.
                existingJustification.markForDeletion();

                // Step 4: Remove the justification from the repository (physical deletion).
                // Leverages Repository Pattern for persistence.
                _justificationRepository.remove(existingJustification);
                System.out.println("JustificationService: Justification with ID " + justificationId + " physically removed from repository.");

                // Step 5: Commit the transaction if all operations were successful.
                _unitOfWork.commit();
                System.out.println("JustificationService: Transaction committed successfully for " + justificationId + ".");
            } else {
                // Alt: Justification Not Found / System Error
                System.out.println("JustificationService: Justification with ID " + justificationId + " not found. Rolling back transaction.");
                // If not found, rollback the transaction.
                _unitOfWork.rollback();
                throw new RuntimeException("Error: Justification with ID " + justificationId + " not found.");
            }
        } catch (RuntimeException e) {
            // Catch any unexpected runtime exceptions during the process (e.g., persistence errors).
            System.err.println("JustificationService: An error occurred during elimination for ID " + justificationId + ": " + e.getMessage());
            // Attempt to roll back the transaction to maintain data consistency.
            // This rollback might also fail if the connection is interrupted.
            try {
                _unitOfWork.rollback();
            } catch (RuntimeException rollbackEx) {
                System.err.println("JustificationService: Error during rollback for ID " + justificationId + ": " + rollbackEx.getMessage());
                // Propagate the original exception as it was the root cause, or combine them.
                throw new RuntimeException("Error: Operation failed and rollback might be incomplete. " + e.getMessage(), e);
            }
            throw new RuntimeException("Error: Operation failed. " + e.getMessage(), e);
        }
        System.out.println("JustificationService: eliminateJustification(" + justificationId + ") completed.");
    }
}