package com.example.editteaching.service;

import com.example.editteaching.model.Teaching;
import com.example.editteaching.repository.TeachingRepository;
import com.example.editteaching.dto.TeachingDTO;
import com.example.editteaching.exception.TeachingNotFoundException;
import com.example.editteaching.exception.InvalidTeachingDataException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for handling business logic related to Teaching entities.
 * This class orchestrates operations between the controller and the repository,
 * including data validation and exception handling.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
@Service
public class TeachingService {

    private final TeachingRepository teachingRepository;

    /**
     * Constructor for TeachingService, injecting TeachingRepository dependency.
     * Spring automatically provides an instance of TeachingRepository.
     *
     * @param teachingRepository The repository for accessing Teaching entities.
     */
    public TeachingService(TeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }

    /**
     * Retrieves a Teaching entity by its unique identifier.
     *
     * @param id The unique ID of the teaching to retrieve.
     * @return The found Teaching entity.
     * @throws TeachingNotFoundException if no teaching with the given ID is found.
     */
    public Teaching getTeachingById(String id) {
        // Attempt to find the teaching by its ID in the repository
        return teachingRepository.findById(id)
                .orElseThrow(() -> new TeachingNotFoundException("Teaching with ID " + id + " not found."));
    }

    /**
     * Updates an existing Teaching entity with new data provided in a DTO.
     * Performs validation on the incoming data before persisting changes.
     *
     * @param id The unique ID of the teaching to update.
     * @param teachingDTO The DTO containing the updated teaching details.
     * @return The updated Teaching entity.
     * @throws TeachingNotFoundException if no teaching with the given ID is found.
     * @throws InvalidTeachingDataException if the provided teaching data is invalid (e.g., business rule violations).
     */
    public Teaching updateTeaching(String id, TeachingDTO teachingDTO) {
        // Step 1: Validate the incoming DTO data against business rules.
        // This covers the "Make checks on the validity of the data entered" requirement.
        validateTeachingData(teachingDTO);

        // Step 2: Retrieve the existing teaching from the archive (database).
        // If not found, activate the "Teaching not found" error case.
        Teaching existingTeaching = teachingRepository.findById(id)
                .orElseThrow(() -> new TeachingNotFoundException("Teaching with ID " + id + " not found. Cannot update."));

        // Step 3: Update the existing teaching entity with the new data from the DTO.
        // The ID remains unchanged as we are updating an existing record.
        existingTeaching.setName(teachingDTO.getName());
        existingTeaching.setCourseCode(teachingDTO.getCourseCode());
        existingTeaching.setInstructor(teachingDTO.getInstructor());
        existingTeaching.setStartDate(teachingDTO.getStartDate());
        existingTeaching.setEndDate(teachingDTO.getEndDate());
        existingTeaching.setDescription(teachingDTO.getDescription());

        // Step 4: Save the updated teaching entity back to the archive.
        // This persists the changes.
        Teaching updatedTeaching = teachingRepository.save(existingTeaching);

        // Postcondition: The user has changed a teaching.
        // The updated teaching object is returned, which can be used to notify the user.
        return updatedTeaching;
    }

    /**
     * Performs business-level validation on the TeachingDTO.
     * This method encapsulates specific business rules that might not be covered by
     * standard Jakarta Bean Validation annotations (e.g., cross-field validation).
     * This corresponds to part of the "Make checks on the validity of the data entered" requirement
     * and activates the "Errodati" use case if validation fails.
     *
     * @param teachingDTO The DTO containing teaching data to validate.
     * @throws InvalidTeachingDataException if any business rule validation fails.
     */
    private void validateTeachingData(TeachingDTO teachingDTO) {
        // Example business rule: Start date must not be after end date.
        if (teachingDTO.getStartDate() != null && teachingDTO.getEndDate() != null &&
            teachingDTO.getStartDate().isAfter(teachingDTO.getEndDate())) {
            throw new InvalidTeachingDataException("Start date cannot be after end date.");
        }
        // Additional business validations can be added here as per requirements.
        // For instance, checking if a course code already exists for a given instructor,
        // or if the description meets certain length criteria beyond @NotBlank.
    }

    /**
     * Retrieves a list of all Teaching entities.
     * This method supports the postcondition "View the list of updated teachings"
     * by providing the full list.
     *
     * @return A list of all Teaching entities.
     */
    public List<Teaching> getAllTeachings() {
        return teachingRepository.findAll();
    }
}