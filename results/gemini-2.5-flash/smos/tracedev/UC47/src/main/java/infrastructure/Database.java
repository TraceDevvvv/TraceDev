package infrastructure;

import domain.ReportCard;
import java.util.HashMap;
import java.util.Map;

/**
 * A mock database class to simulate persistence storage.
 * It uses in-memory maps to store data.
 */
public class Database {
    private Map<String, ReportCard> reportCardsById;
    private Map<String, String> reportCardIdByStudentId; // Maps studentId to reportCardId

    // A flag to simulate network/persistence errors
    private boolean simulatePersistenceError = false;

    public Database() {
        this.reportCardsById = new HashMap<>();
        this.reportCardIdByStudentId = new HashMap<>();
        // Initialize with some dummy data
        ReportCard rc1 = new ReportCard("RC456", "S123", new HashMap<String, Integer>() {{
            put("MATH101", 85);
            put("ENG202", 92);
            put("PHY303", 78);
        }});
        reportCardsById.put(rc1.getId(), rc1);
        reportCardIdByStudentId.put(rc1.getStudentId(), rc1.getId());
    }

    /**
     * Sets the flag to simulate a persistence error.
     * @param simulatePersistenceError If true, repository operations might throw PersistenceException.
     */
    public void setSimulatePersistenceError(boolean simulatePersistenceError) {
        this.simulatePersistenceError = simulatePersistenceError;
    }

    /**
     * Retrieves a ReportCard by its ID. Simulates a DB SELECT operation.
     * @param reportCardId The ID of the report card.
     * @return The ReportCard object, or null if not found.
     * @throws PersistenceException if `simulatePersistenceError` is true.
     */
    public ReportCard selectReportCardById(String reportCardId) throws PersistenceException {
        if (simulatePersistenceError) {
            throw new PersistenceException("DB SELECT: Network connection failed during report card retrieval.");
        }
        System.out.println("DEBUG: DB SELECT * FROM ReportCard WHERE id = '" + reportCardId + "'");
        return reportCardsById.get(reportCardId);
    }

    /**
     * Retrieves a ReportCard by student ID. Simulates a DB SELECT operation.
     * @param studentId The ID of the student.
     * @return The ReportCard object, or null if not found.
     * @throws PersistenceException if `simulatePersistenceError` is true.
     */
    public ReportCard selectReportCardByStudentId(String studentId) throws PersistenceException {
        if (simulatePersistenceError) {
            throw new PersistenceException("DB SELECT: Network connection failed during report card retrieval by student ID.");
        }
        System.out.println("DEBUG: DB SELECT * FROM ReportCard WHERE studentId = '" + studentId + "'");
        String reportCardId = reportCardIdByStudentId.get(studentId);
        return reportCardId != null ? reportCardsById.get(reportCardId) : null;
    }

    /**
     * Saves a ReportCard. Simulates a DB UPDATE/INSERT operation.
     * @param reportCard The ReportCard object to save.
     * @throws PersistenceException if `simulatePersistenceError` is true.
     */
    public void saveReportCard(ReportCard reportCard) throws PersistenceException {
        if (simulatePersistenceError) {
            throw new PersistenceException("DB UPDATE: Network connection failed during report card save.");
        }
        System.out.println("DEBUG: DB UPDATE/INSERT ReportCard SET grades = " + reportCard.getGrades() + " WHERE id = '" + reportCard.getId() + "'");
        reportCardsById.put(reportCard.getId(), reportCard);
        reportCardIdByStudentId.put(reportCard.getStudentId(), reportCard.getId());
    }
}