package com.example.absence.infrastructure;

import com.example.absence.domain.Absence;
import java.util.Date;
import java.util.List;

/**
 * Repository interface for absence persistence operations.
 */
public interface IAbsenceRepository {
    /**
     * Saves a list of absence records.
     * @param absences the list of absences to save
     * @throws PersistenceException if a database error occurs
     */
    void saveAbsences(List<Absence> absences) throws PersistenceException;

    /**
     * Finds absences for a given class and date.
     * @param classId the class ID
     * @param date the date
     * @return list of absences
     */
    List<Absence> findByClassAndDate(String classId, Date date);
}