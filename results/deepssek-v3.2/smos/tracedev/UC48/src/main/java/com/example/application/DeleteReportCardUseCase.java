package com.example.application;

import com.example.domain.ReportCard;
import com.example.infrastructure.ReportCardRepository;
import com.example.infrastructure.SMOSService;
import java.util.List;
import java.util.Optional;

/**
 * Use case for deleting a report card.
 */
public class DeleteReportCardUseCase {
    private ReportCardRepository reportCardRepository;
    private SMOSService smosService;

    public DeleteReportCardUseCase(ReportCardRepository repo, SMOSService smosService) {
        this.reportCardRepository = repo;
        this.smosService = smosService;
    }

    /**
     * Executes deletion of a report card.
     * Includes validation and server communication.
     */
    public Result execute(String reportCardId) {
        // Validate report card exists
        Optional<ReportCard> found = reportCardRepository.findById(reportCardId);
        if (!found.isPresent()) {
            return new Result(false, "Report card not found");
        }

        // Delete from repository (which will call SMOS server)
        boolean deleted = reportCardRepository.delete(reportCardId);
        if (deleted) {
            // Clean up connection after successful deletion
            smosService.disconnect();
            return new Result(true, "Deleted successfully");
        } else {
            return new Result(false, "Deletion failed on server");
        }
    }

    /**
     * Returns all report cards (for initial loading).
     */
    public List<ReportCard> getAllReportCards() {
        return reportCardRepository.findAll();
    }
}