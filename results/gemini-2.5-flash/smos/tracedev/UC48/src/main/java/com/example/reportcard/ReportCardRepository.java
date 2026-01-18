package com.example.reportcard;

/**
 * Interface for managing ReportCard persistence.
 * Specifies methods for finding and deleting report cards.
 * Implements REQ-004: Consistent Delete Operation Outcome (delete returns boolean).
 * Note: Methods like findById and delete may throw specific exceptions for not-found
 * or persistence errors to satisfy REQ-QR-SystemStability.
 */
public interface ReportCardRepository {

    /**
     * Finds a ReportCard by its unique identifier.
     *
     * @param reportCardId The ID of the report card to find.
     * @return The ReportCard object if found, otherwise null.
     */
    ReportCard findById(String reportCardId);

    /**
     * Deletes a ReportCard. This operation is expected to handle
     * the persistence layer interaction for deletion.
     *
     * @param reportCard The ReportCard object to delete.
     * @return true if the report card was successfully deleted, false otherwise.
     */
    boolean delete(ReportCard reportCard);
}