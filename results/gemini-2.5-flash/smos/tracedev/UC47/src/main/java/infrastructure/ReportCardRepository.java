package infrastructure;

import domain.ReportCard;

/**
 * Concrete implementation of IReportCardRepository.
 * Interacts with the simulated Database for persistence.
 */
public class ReportCardRepository implements IReportCardRepository {
    private Database database;

    /**
     * Constructs a ReportCardRepository with a reference to the Database.
     * @param database The database instance to use for persistence.
     */
    public ReportCardRepository(Database database) {
        this.database = database;
    }

    @Override
    public ReportCard findById(String reportCardId) throws PersistenceException {
        // Delegates to the mock database.
        return database.selectReportCardById(reportCardId);
    }

    @Override
    public ReportCard findByStudentId(String studentId) throws PersistenceException {
        // Delegates to the mock database.
        return database.selectReportCardByStudentId(studentId);
    }

    @Override
    public void save(ReportCard reportCard) throws PersistenceException {
        // Delegates to the mock database.
        database.saveReportCard(reportCard);
    }
}