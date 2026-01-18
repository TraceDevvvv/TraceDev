package com.example.digitalregister.service;

import com.example.digitalregister.model.DigitalRegister;
import com.example.digitalregister.repository.IRegisterRepository;
import com.example.digitalregister.exceptions.DataAccessError;

import java.util.List;

/**
 * Business logic service for managing digital registers.
 * It uses IRegisterRepository to access register data.
 */
public class RegisterService {

    private final IRegisterRepository registerRepository; // Composition: RegisterService *-- IRegisterRepository

    /**
     * Constructs a RegisterService with a given IRegisterRepository.
     * @param registerRepository The repository to use for accessing register data.
     */
    public RegisterService(IRegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    /**
     * Retrieves a list of digital registers for a specific academic year.
     * @param yearId The unique identifier of the academic year.
     * @return A list of DigitalRegister objects.
     * @throws DataAccessError If there is an issue accessing the underlying data source.
     */
    public List<DigitalRegister> getRegistersByAcademicYear(String yearId) {
        System.out.println("[RegisterService] Requesting registers for academic year: " + yearId);
        try {
            // Delegates call to repository
            return registerRepository.findByAcademicYear(yearId);
        } catch (DataAccessError e) {
            // Propagates the DataAccessError from the repository layer
            System.err.println("[RegisterService] Error in data retrieval: " + e.getMessage());
            throw e; // Re-throw the exception to be handled by the controller
        }
    }
}