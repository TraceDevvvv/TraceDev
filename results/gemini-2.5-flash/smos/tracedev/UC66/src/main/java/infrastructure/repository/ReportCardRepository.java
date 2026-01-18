package infrastructure.repository;

import infrastructure.dao.ReportCardDAO;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation of IReportCardRepository.
 * This class simulates interaction with a database to retrieve report card data.
 */
public class ReportCardRepository implements IReportCardRepository {

    // Dummy data to simulate database records
    private final List<ReportCardDAO> reportCards = Arrays.asList(
        new ReportCardDAO("RC001", "S001", "Q1", "Math:A,Science:B+,History:B", "Alice showed good progress this quarter."),
        new ReportCardDAO("RC002", "S002", "Q1", "Math:B,Science:C,History:A-", "Bob needs to improve in science."),
        new ReportCardDAO("RC003", "S001", "Q2", "Math:A-,Science:A,History:B+", "Alice continues to excel."),
        new ReportCardDAO("RC004", "S005", "Q1", "Math:C+,Science:B-,History:C", "Clark needs to focus more on all subjects.")
    );

    /**
     * Finds a report card by student ID and quadrimestre ID.
     * @param studentId The ID of the student.
     * @param quadrimestreId The ID of the quadrimestre.
     * @return The ReportCardDAO object if found, otherwise null.
     */
    @Override
    public ReportCardDAO findByStudentAndQuadrimestre(String studentId, String quadrimestreId) {
        System.out.println("Database: SELECT * FROM report_cards WHERE student_id = " + studentId + " AND quadrimestre_id = " + quadrimestreId); // Simulate DB call
        return reportCards.stream()
                          .filter(rc -> rc.getStudentId().equals(studentId) && rc.getQuadrimestreId().equals(quadrimestreId))
                          .findFirst()
                          .orElse(null);
    }
}