package com.example.monitoring.infrastructure;

import com.example.monitoring.domain.SchoolYear;
import com.example.monitoring.domain.Student;
import com.example.monitoring.infrastructure.mock.DatabaseConnection; // Using mock for demonstration

import java.util.List;

/**
 * Infrastructure Layer: Concrete implementation of {@link IStudentRepository} for database access.
 * This class interacts with a database (simulated here) to retrieve student information.
 */
public class StudentDatabaseRepository implements IStudentRepository {
    // Represents a mechanism for connecting to the database
    private DatabaseConnection dbConnection;
    // Dependency for logging errors
    private IErrorLogger errorLogger;

    /**
     * Constructor for StudentDatabaseRepository.
     * @param dbConnection An object representing the database connection (mocked here).
     * @param errorLogger The logger to use for recording errors.
     */
    public StudentDatabaseRepository(DatabaseConnection dbConnection, IErrorLogger errorLogger) {
        this.dbConnection = dbConnection;
        this.errorLogger = errorLogger;
    }

    /**
     * {@inheritDoc}
     *
     * This implementation simulates querying a database. It can also simulate
     * a database connection failure for testing purposes.
     *
     * @throws DatabaseConnectionException if the simulated database connection fails.
     */
    @Override
    public List<Student> findStudentsExceedingThresholds(SchoolYear schoolYear, int absenceThreshold, int notesThreshold)
            throws DatabaseConnectionException {
        System.out.println("StudentRepository: Searching database for students exceeding thresholds " +
                "(Year: " + schoolYear.getYear() + ", Absence > " + absenceThreshold + ", Notes > " + notesThreshold + ").");

        try {
            // Simulate interaction with the database. This method might throw DatabaseConnectionException.
            List<Student> students = dbConnection.findStudentsExceedingThresholds(schoolYear, absenceThreshold, notesThreshold);
            System.out.println("StudentRepository: Database query successful.");
            return students;
        } catch (DatabaseConnectionException e) {
            // If a connection error occurs, log it and then re-throw for upstream handling (Service Layer).
            System.err.println("StudentRepository: Database connection interrupted.");
            errorLogger.logError("DB connection lost during findStudentsExceedingThresholds.", e);
            throw e; // Re-throw the exception
        }
    }
}