package com.example.repository;

import com.example.entity.Absence;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple in-memory implementation of AbsenceRepository for demonstration.
 */
public class AbsenceRepositoryImpl implements AbsenceRepository {
    // Simulating a data store
    private List<Absence> absenceStore;

    public AbsenceRepositoryImpl() {
        absenceStore = new ArrayList<>();
        // Pre-populate with sample data for testing
    }

    @Override
    public List<Absence> findAbsencesByStudentId(String studentId) {
        // In a real implementation, this would query a database
        // For now, simulate a SQL query: SELECT * FROM Absence WHERE studentId = ?
        // Simulate empty result for specific student IDs if needed
        if ("empty".equals(studentId)) {
            return new ArrayList<>(); // Returns empty list for sequence diagram m10
        }
        return new ArrayList<>(absenceStore);
    }

    @Override
    public List<Absence> selectAllFromAbsenceWhereStudentId(String studentId) {
        return findAbsencesByStudentId(studentId);
    }

    /**
     * Helper method to add sample absences for testing.
     * @param absence the absence to add
     */
    public void addAbsence(Absence absence) {
        absenceStore.add(absence);
    }
}