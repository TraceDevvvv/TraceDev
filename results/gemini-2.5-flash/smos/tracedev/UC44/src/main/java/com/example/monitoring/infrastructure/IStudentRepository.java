package com.example.monitoring.infrastructure;

import com.example.monitoring.domain.SchoolYear;
import com.example.monitoring.domain.Student;

import java.util.List;

/**
 * Infrastructure Layer: Interface for accessing student data from a persistent store.
 * This defines the contract for how student entities are retrieved from the database.
 */
public interface IStudentRepository {

    /**
     * Finds students who exceed the specified absence and notes thresholds for a given school year.
     * @param schoolYear The school year for which to retrieve student data.
     * @param absenceThreshold The threshold for student absences.
     * @param notesThreshold The threshold for student notes.
     * @return A list of {@link Student} objects that meet the criteria.
     * @throws DatabaseConnectionException if there is an issue connecting to the database.
     */
    List<Student> findStudentsExceedingThresholds(SchoolYear schoolYear, int absenceThreshold, int notesThreshold)
            throws DatabaseConnectionException;
}