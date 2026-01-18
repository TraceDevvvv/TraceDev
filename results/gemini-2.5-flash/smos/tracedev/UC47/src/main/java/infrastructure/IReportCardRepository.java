package infrastructure;

import domain.ReportCard;

/**
 * Interface for Report Card repository operations.
 * Defines the contract for data access specific to ReportCards.
 * Modified to satisfy REQ-005, specifying methods throw PersistenceException.
 */
public interface IReportCardRepository {
    /**
     * Finds a ReportCard by its unique identifier.
     * @param reportCardId The ID of the report card to find.
     * @return The found ReportCard, or null if not found.
     * @throws PersistenceException if an error occurs during data access.
     */
    ReportCard findById(String reportCardId) throws PersistenceException;

    /**
     * Finds a ReportCard by the student's unique identifier.
     * Added for consistency with AppService signature and REQ-005.
     * @param studentId The ID of the student whose report card is to be found.
     * @return The found ReportCard, or null if not found.
     * @throws PersistenceException if an error occurs during data access.
     */
    ReportCard findByStudentId(String studentId) throws PersistenceException;

    /**
     * Saves a ReportCard to the persistence store.
     * If the report card exists, it is updated; otherwise, it is created.
     * Modified to satisfy REQ-005, specifying method throws PersistenceException.
     * @param reportCard The ReportCard to save.
     * @throws PersistenceException if an error occurs during data access.
     */
    void save(ReportCard reportCard) throws PersistenceException;
}