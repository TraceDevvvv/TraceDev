package com.example.app;

import java.util.ArrayList;
import java.util.List;

/**
 * TeachingValidator is responsible for validating TeachingData.
 * It uses an ITeachingRepository to check for existing teaching names.
 */
public class TeachingValidator {
    private final ITeachingRepository repository; // Dependency on the repository to check for uniqueness

    /**
     * Constructs a new TeachingValidator with a given repository.
     *
     * @param repository The repository used to perform validation checks.
     */
    public TeachingValidator(ITeachingRepository repository) {
        this.repository = repository;
    }

    /**
     * Validates the provided TeachingData.
     * Checks if the teaching name is null/empty and if a teaching with the same name already exists.
     *
     * @param teachingData The data to validate.
     * @return A list of error messages. An empty list indicates that the data is valid.
     */
    public List<String> validate(TeachingData teachingData) {
        List<String> errors = new ArrayList<>();

        // R7: Name cannot be empty or null
        if (teachingData.name == null || teachingData.name.trim().isEmpty()) {
            errors.add("Teaching name cannot be empty.");
        }

        // R8: Teaching name must be unique
        // Only check uniqueness if name is not empty, to avoid duplicate errors for empty names
        if (teachingData.name != null && !teachingData.name.trim().isEmpty()) {
            if (repository.existsByName(teachingData.name.trim())) {
                errors.add("Teaching with name '" + teachingData.name + "' already exists.");
            }
        }

        return errors;
    }
}