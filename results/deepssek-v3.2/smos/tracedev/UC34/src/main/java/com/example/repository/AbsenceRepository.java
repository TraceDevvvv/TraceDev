package com.example.repository;

import com.example.entity.Absence;
import java.util.List;

/**
 * Repository interface for Absence data access.
 */
public interface AbsenceRepository {
    /**
     * Finds all absences for a given student.
     * @param studentId the student identifier
     * @return list of absences for the student
     */
    List<Absence> findAbsencesByStudentId(String studentId);

    /**
     * Method corresponding to sequence message m8: SELECT * FROM Absence WHERE studentId = ?
     * This is essentially the same as findAbsencesByStudentId but with a different name.
     */
    List<Absence> selectAllFromAbsenceWhereStudentId(String studentId);
}