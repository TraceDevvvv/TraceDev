package infrastructure.repository;

import infrastructure.dao.ReportCardDAO;

/**
 * Interface for Report Card data access operations.
 * Defines the contract for retrieving report card data from the persistence layer.
 */
public interface IReportCardRepository {
    /**
     * Finds a report card for a specific student and quadrimestre.
     * @param studentId The ID of the student.
     * @param quadrimestreId The ID of the quadrimestre.
     * @return The ReportCardDAO object if found, otherwise null.
     */
    ReportCardDAO findByStudentAndQuadrimestre(String studentId, String quadrimestreId);
}