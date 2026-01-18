package com.example.diagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for register-related business logic.
 */
public class RegisterService {
    private RegisterRepository repository;

    public RegisterService(RegisterRepository repository) {
        this.repository = repository;
    }

    public RegisterRepository getRepository() {
        return repository;
    }

    public void setRepository(RegisterRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds registers by academic year.
     * @param year the academic year.
     * @return list of Register objects.
     */
    public List<Register> findRegistersByAcademicYear(String year) {
        return repository.findByAcademicYear(year);
    }

    /**
     * Converts a list of Register entities to RegisterDTO objects per requirement REQ-004.
     * This corresponds to sequence message m19: convert to DTOs.
     * @param registers the list of Register entities.
     * @return list of RegisterDTO objects.
     */
    public List<RegisterDTO> convertToDTO(List<Register> registers) {
        System.out.println("Converting Register objects to DTOs");
        List<RegisterDTO> dtos = new ArrayList<>();
        for (Register reg : registers) {
            dtos.add(new RegisterDTO(reg));
        }
        return dtos;
    }
}