package com.example;

import java.util.ArrayList;
import java.util.List;

// Validates the data in a ClassCreationDTO.
public class ClassValidator {

    /**
     * Validates the fields of a ClassCreationDTO.
     *
     * @param dto The ClassCreationDTO to validate.
     * @return A list of error messages. An empty list indicates valid data.
     */
    public List<String> validate(ClassCreationDTO dto) {
        System.out.println("ClassValidator: Validating DTO for Class creation...");
        List<String> errors = new ArrayList<>();

        if (dto.name == null || dto.name.trim().isEmpty()) {
            errors.add("Class name cannot be empty.");
        }
        if (dto.address == null || dto.address.trim().isEmpty()) {
            errors.add("Class address cannot be empty.");
        }
        if (dto.academicYear == null || dto.academicYear.trim().isEmpty()) {
            errors.add("Academic year cannot be empty.");
        }
        // Add more validation rules as needed, e.g., format for academic year, length constraints

        if (errors.isEmpty()) {
            System.out.println("ClassValidator: DTO is valid.");
        } else {
            System.out.println("ClassValidator: DTO validation failed with " + errors.size() + " errors.");
        }
        return errors;
    }
}