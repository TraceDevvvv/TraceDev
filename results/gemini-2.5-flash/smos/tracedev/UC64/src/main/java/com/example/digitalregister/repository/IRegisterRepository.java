package com.example.digitalregister.repository;

import com.example.digitalregister.model.DigitalRegister;
import java.util.List;

/**
 * Interface for repository operations related to DigitalRegister entities.
 * Defines the contract for data access specific to digital registers.
 */
public interface IRegisterRepository {

    /**
     * Finds and returns a list of digital registers associated with a specific academic year.
     * @param yearId The unique identifier of the academic year.
     * @return A list of DigitalRegister objects for the given academic year.
     *         Returns an empty list if no registers are found.
     *         May throw DataAccessError if there's an issue with the underlying data source.
     */
    List<DigitalRegister> findByAcademicYear(String yearId);
}