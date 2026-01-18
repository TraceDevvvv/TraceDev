package com.example.infrastructure.persistence;

import com.example.application.ports.AbsenceRepository;
import com.example.domain.Absence;
import java.sql.Connection;

/**
 * Implementation of AbsenceRepository using a database connection.
 */
public class AbsenceRepositoryImpl implements AbsenceRepository {
    private Connection databaseConnection;

    public AbsenceRepositoryImpl(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Absence findById(String id) {
        // Simulate database SELECT
        System.out.println("AbsenceRepositoryImpl: SELECT absence WHERE id = " + id);
        // Return a dummy absence for illustration
        return new Absence(id, "student123", new java.util.Date(),
                com.example.domain.AbsenceStatus.ABSENT, "reason");
    }

    @Override
    public Absence save(Absence absence) {
        // Simulate database UPDATE
        System.out.println("AbsenceRepositoryImpl: UPDATE absence SET ... WHERE id = " + absence.getId());
        return absence;
    }

    // New method for sequence diagram message
    public void selectAbsenceWhereId(String id) {
        System.out.println("AbsenceRepositoryImpl: SELECT absence WHERE id = " + id);
    }

    public void updateAbsenceSetWhereId(String id) {
        System.out.println("AbsenceRepositoryImpl: UPDATE absence SET ... WHERE id = " + id);
    }
}