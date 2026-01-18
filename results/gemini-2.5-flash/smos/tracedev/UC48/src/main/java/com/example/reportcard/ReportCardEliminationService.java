package com.example.reportcard;

import java.util.Objects;

/**
 * Service responsible for the elimination (deletion) of report cards.
 * It orchestrates the process of finding, marking as deleted, and persisting the deletion.
 */
public class ReportCardEliminationService {
    private final ReportCardRepository reportCardRepository;

    /**
     * Constructs a ReportCardEliminationService with a dependency on ReportCardRepository.
     *
     * @param reportCardRepository The repository to interact with report card data.
     */
    public ReportCardEliminationService(ReportCardRepository reportCardRepository) {
        this.reportCardRepository = Objects.requireNonNull(reportCardRepository, "ReportCardRepository cannot be null");
    }

    /**
     * Eliminates a report card identified by its ID.
     * This method implements the core logic for deleting a report card as
     * depicted in the sequence diagram.
     *
     * @param reportCardId The ID of the report card to eliminate.
     * @return true if the report card was successfully eliminated, false otherwise.
     */
    public boolean eliminateReportCard(String reportCardId) {
        System.out.println("[ReportCardEliminationService] Attempting to eliminate report card with ID: " + reportCardId);

        // Find the report card
        ReportCard reportCard = reportCardRepository.findById(reportCardId);
        if (reportCard == null) {
            System.out.println("[ReportCardEliminationService] Report Card with ID " + reportCardId + " not found. Elimination failed.");
            return false;
        }

        // Mark domain object as deleted logically
        reportCard.markAsDeleted();

        // Persist the deletion (which could be a physical delete or an update based on isDeleted flag)
        boolean deletionSuccess = reportCardRepository.delete(reportCard);

        if (deletionSuccess) {
            System.out.println("[ReportCardEliminationService] Report Card " + reportCardId + " successfully eliminated.");
        } else {
            System.out.println("[ReportCardEliminationService] Failed to eliminate report card " + reportCardId + " in the repository.");
        }
        return deletionSuccess;
    }
}