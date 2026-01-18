package com.example.diagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of RegisterRepository.
 */
public class RegisterRepositoryImpl implements RegisterRepository {
    private ArchiveRepository archiveRepository;

    public RegisterRepositoryImpl() {
        this.archiveRepository = new ArchiveRepositoryImpl();
    }

    public RegisterRepositoryImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    /**
     * Finds registers by academic year.
     * Simulates database query.
     * This corresponds to sequence message m15 (via ArchiveRepository) and m16 (return data).
     * @param year the academic year.
     * @return list of Register objects.
     */
    @Override
    public List<Register> findByAcademicYear(String year) {
        // m15: query registers for year (delegate to ArchiveRepository)
        List<Register> registerData = archiveRepository.queryRegistersForYear(year);
        // m16: register data returned
        // m17: convert to Register objects (already Register objects, but we can still log)
        System.out.println("Converting raw data to Register objects");
        return registerData;
    }
}