package com.example.app;

import java.util.List;
import java.util.UUID;

/**
 * InsertTeachingUseCase handles the business logic for inserting a new teaching.
 * It orchestrates validation and persistence of teaching data.
 */
public class InsertTeachingUseCase {
    private final ITeachingRepository repository; // Dependency for persisting teaching data
    private final TeachingValidator validator;   // Dependency for validating teaching data

    /**
     * Constructs a new InsertTeachingUseCase.
     *
     * @param repository The repository to save the teaching.
     * @param validator  The validator to validate the teaching data.
     */
    public InsertTeachingUseCase(ITeachingRepository repository, TeachingValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    /**
     * Executes the use case to insert a new teaching.
     * It validates the data, creates a Teaching entity, and saves it.
     *
     * @param teachingData The data for the new teaching.
     * @throws InvalidTeachingDataException if the teaching data is invalid.
     * @throws PersistenceException if an error occurs during persistence.
     */
    public void execute(TeachingData teachingData) {
        // Step 1: Validate the teaching data
        // Sequence Diagram: UseCase -> Validator: validate(teachingData)
        List<String> validationErrors = validator.validate(teachingData);

        // Sequence Diagram: alt Data is Invalid
        if (!validationErrors.isEmpty()) {
            // If validation fails, throw an exception with the errors.
            throw new InvalidTeachingDataException(validationErrors);
        }

        // Sequence Diagram: alt Data is Valid
        // Step 2: Create a new Teaching entity
        // Sequence Diagram: UseCase -> UseCase: createTeaching(id : UUID, name : teachingData.name)
        String teachingId = UUID.randomUUID().toString(); // Generate a unique ID for the new teaching
        Teaching newTeaching = new Teaching(teachingId, teachingData.name);
        System.out.println("[UseCase] Created Teaching: " + newTeaching); // For demonstration

        // Step 3: Save the new Teaching entity
        // Sequence Diagram: UseCase -> RepoInterface: save(teaching : Teaching)
        try {
            repository.save(newTeaching);
            System.out.println("[UseCase] Teaching saved successfully to repository.");
        } catch (PersistenceException e) {
            // Sequence Diagram: Database connection interrupted -> RepoInterface --> UseCase: exception : PersistenceError
            // Re-throw PersistenceException as it's a domain-specific error that AdministratorPresenter can handle.
            System.err.println("[UseCase] Error saving teaching: " + e.getMessage());
            throw e;
        }
    }
}