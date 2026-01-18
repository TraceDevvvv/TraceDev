package com.example.usecase;

import com.example.exceptions.RepositoryException;
import com.example.exceptions.ServiceException;
import com.example.model.Teaching;
import com.example.model.TeachingDTO;
import com.example.model.ValidationResult;
import com.example.repo.TeachingRepository;
import com.example.validator.TeachingValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case responsible for editing teaching details.
 * Orchestrates business logic involving validation and persistence.
 */
public class EditTeachingUseCase {
    private final TeachingRepository teachingRepository;
    private final TeachingValidator teachingValidator;

    /**
     * Constructs an EditTeachingUseCase.
     * @param repo The repository for teaching data.
     * @param validator The validator for teaching DTOs.
     */
    public EditTeachingUseCase(TeachingRepository repo, TeachingValidator validator) {
        this.teachingRepository = repo;
        this.teachingValidator = validator;
    }

    /**
     * Edits an existing teaching with the provided data.
     * @param editedTeachingDTO The DTO containing the updated teaching details.
     * @return A ValidationResult indicating if the edit operation was successful or if there were validation errors.
     * @throws ServiceException if a repository error occurs during the operation (e.g., connection issue).
     */
    public ValidationResult editTeaching(TeachingDTO editedTeachingDTO) throws ServiceException {
        // 1. Validate the incoming DTO
        ValidationResult validationResult = teachingValidator.validate(editedTeachingDTO);
        if (validationResult.hasErrors()) {
            System.out.println("USECASE: Validation errors found.");
            return validationResult; // Return validation errors to the caller
        }

        try {
            // 2. Retrieve the existing Teaching entity
            System.out.println("USECASE: Finding teaching by ID: " + editedTeachingDTO.id);
            Teaching existingTeaching = teachingRepository.findById(editedTeachingDTO.id);

            // 3. Update the entity's details
            System.out.println("USECASE: Updating details for teaching: " + existingTeaching.getId());
            existingTeaching.updateDetails(editedTeachingDTO);

            // 4. Persist the updated entity
            System.out.println("USECASE: Saving updated teaching to repository.");
            teachingRepository.update(existingTeaching);

            System.out.println("USECASE: Teaching updated successfully.");
            // Return a success validation result
            return new ValidationResult(true, List.of());
        } catch (RepositoryException e) {
            // Wrap repository exceptions in ServiceException to maintain layer abstraction
            System.err.println("USECASE: Repository error during editTeaching: " + e.getMessage());
            throw new ServiceException("Failed to edit teaching due to a repository error: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a list of all teachings as DTOs.
     * @return A list of TeachingDTOs.
     * @throws ServiceException if a repository error occurs during retrieval.
     */
    public List<TeachingDTO> getTeachingsList() throws ServiceException {
        try {
            System.out.println("USECASE: Retrieving all teachings from repository.");
            List<Teaching> allTeachings = teachingRepository.findAll();
            // Convert Teaching entities to DTOs for presentation
            return allTeachings.stream()
                               .map(t -> new TeachingDTO(t.getId(), t.getName(), t.getDescription()))
                               .collect(Collectors.toList());
        } catch (RepositoryException e) {
            System.err.println("USECASE: Repository error during getTeachingsList: " + e.getMessage());
            throw new ServiceException("Failed to retrieve teachings list due to a repository error: " + e.getMessage(), e);
        }
    }
}