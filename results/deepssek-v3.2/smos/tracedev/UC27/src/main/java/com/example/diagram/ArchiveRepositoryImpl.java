package com.example.diagram;

import java.util.List;

/**
 * Implementation of ArchiveRepository that simulates database access.
 */
public class ArchiveRepositoryImpl implements ArchiveRepository {
    /**
     * Query registers for a given academic year.
     * Simulates database query and returns register data.
     * This corresponds to sequence message m15: query registers for year.
     * @param year the academic year.
     * @return list of Register objects (register data).
     */
    @Override
    public List<Register> queryRegistersForYear(String year) {
        System.out.println("Database queried for registers of year: " + year);
        // Simulate database access by delegating to RegisterRepositoryImpl
        RegisterRepositoryImpl repo = new RegisterRepositoryImpl();
        return repo.findByAcademicYear(year);
    }
}