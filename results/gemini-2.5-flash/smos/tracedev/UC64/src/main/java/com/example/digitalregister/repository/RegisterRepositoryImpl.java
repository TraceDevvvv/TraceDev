package com.example.digitalregister.repository;

import com.example.digitalregister.datasource.ArchiveDataSource;
import com.example.digitalregister.exceptions.DataAccessError;
import com.example.digitalregister.model.DigitalRegister;

import java.util.List;

/**
 * Concrete implementation of IRegisterRepository, interacting with ArchiveDataSource.
 */
public class RegisterRepositoryImpl implements IRegisterRepository {

    private final ArchiveDataSource archiveDataSource; // Composition: RegisterRepositoryImpl *-- ArchiveDataSource

    /**
     * Constructs a RegisterRepositoryImpl with a given ArchiveDataSource.
     * @param archiveDataSource The data source to retrieve register data from.
     */
    public RegisterRepositoryImpl(ArchiveDataSource archiveDataSource) {
        this.archiveDataSource = archiveDataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DigitalRegister> findByAcademicYear(String yearId) {
        System.out.println("[RegisterRepositoryImpl] Searching for registers by academic year: " + yearId);
        try {
            // Delegates call to ArchiveDataSource
            return archiveDataSource.retrieveRegisters(yearId);
        } catch (DataAccessError e) {
            // If ArchiveDataSource throws an error, re-throw it as a DataAccessError
            System.err.println("[RegisterRepositoryImpl] Error accessing archive: " + e.getMessage());
            throw new DataAccessError("Failed to retrieve registers from archive: " + e.getMessage(), e);
        }
    }
}