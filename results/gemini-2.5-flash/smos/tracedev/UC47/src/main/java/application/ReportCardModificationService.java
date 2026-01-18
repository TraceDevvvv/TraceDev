package application;

import domain.ReportCard;
import infrastructure.IReportCardRepository;
import infrastructure.PersistenceException;
import java.util.Map;

/**
 * Application service responsible for business logic related to Report Card modification.
 */
public class ReportCardModificationService {
    private IReportCardRepository reportCardRepository;

    /**
     * Constructs a ReportCardModificationService.
     * @param reportCardRepository The repository to use for ReportCard data access.
     */
    public ReportCardModificationService(IReportCardRepository reportCardRepository) {
        this.reportCardRepository = reportCardRepository;
    }

    /**
     * Retrieves the report card details for a given student.
     * @param studentId The ID of the student.
     * @return The ReportCard object for the student, or null if not found.
     */
    public ReportCard getReportCardDetails(String studentId) {
        System.out.println("DEBUG: AppService: getReportCardDetails for studentId: " + studentId);
        try {
            // Modified for consistency with CD and REQ-005: findByStudentId
            ReportCard reportCard = reportCardRepository.findByStudentId(studentId);
            if (reportCard == null) {
                System.out.println("DEBUG: AppService: No report card found for studentId: " + studentId);
            }
            return reportCard;
        } catch (PersistenceException e) {
            System.err.println("ERROR: AppService: Persistence error retrieving report card for studentId " + studentId + ": " + e.getMessage());
            // In a real application, you might log this, re-throw a higher-level exception, or return null.
            return null; // Indicating failure to retrieve.
        }
    }

    /**
     * Updates the grades in a student's report card.
     * @param studentId The ID of the student whose report card is to be updated.
     * @param newGrades A map of new grades (Subject ID -> Score).
     * @return true if the update was successful, false otherwise (e.g., persistence error, report card not found).
     */
    public boolean updateReportCard(String studentId, Map<String, Integer> newGrades) {
        System.out.println("DEBUG: AppService: updateReportCard for studentId: " + studentId + " with new grades: " + newGrades);
        try {
            // Retrieve the existing report card
            ReportCard existingReportCard = reportCardRepository.findByStudentId(studentId); // Modified for consistency
            if (existingReportCard == null) {
                System.err.println("ERROR: AppService: Report card not found for studentId: " + studentId + ". Cannot update.");
                return false;
            }

            // Apply the new grades to the domain object
            for (Map.Entry<String, Integer> entry : newGrades.entrySet()) {
                existingReportCard.updateGrade(entry.getKey(), entry.getValue());
            }

            // Save the updated report card
            reportCardRepository.save(existingReportCard);
            System.out.println("DEBUG: AppService: Report card for studentId " + studentId + " updated and saved successfully.");
            return true;
        } catch (PersistenceException e) {
            System.err.println("ERROR: AppService: Persistence error updating report card for studentId " + studentId + ": " + e.getMessage());
            // Signaled by CD and REQ-006: Return false on persistence exception.
            return false;
        }
    }
}