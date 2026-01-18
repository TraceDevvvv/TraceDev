package com.example.editteaching.controller;

import com.example.editteaching.dto.TeachingDTO;
import com.example.editteaching.model.Teaching;
import com.example.editteaching.service.TeachingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling teaching-related requests.
 * This class exposes endpoints for retrieving and updating teaching details.
 * It interacts with the {@link TeachingService} to perform business operations.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
@RestController // Marks this class as a REST controller, handling incoming HTTP requests
@RequestMapping("/api/teachings") // Base path for all endpoints in this controller
public class TeachingController {

    private final TeachingService teachingService;

    /**
     * Constructor for TeachingController, injecting TeachingService dependency.
     * Spring's dependency injection mechanism automatically provides an instance of TeachingService.
     *
     * @param teachingService The service layer component for teaching operations.
     */
    public TeachingController(TeachingService teachingService) {
        this.teachingService = teachingService;
    }

    /**
     * Endpoint to retrieve a specific teaching by its ID.
     * Corresponds to the "displaydeddailsigning" precondition mentioned in the use case.
     *
     * @param id The unique identifier of the teaching to retrieve.
     * @return A {@link ResponseEntity} containing the {@link Teaching} object if found,
     *         or an appropriate error status (e.g., 404 Not Found) handled by {@link com.example.editteaching.exception.GlobalExceptionHandler}.
     */
    @GetMapping("/{id}") // Maps HTTP GET requests to /api/teachings/{id}
    public ResponseEntity<Teaching> getTeaching(@PathVariable String id) {
        // Delegate the call to the service layer to fetch the teaching by ID.
        // The service layer will throw TeachingNotFoundException if not found,
        // which is then handled by GlobalExceptionHandler.
        Teaching teaching = teachingService.getTeachingById(id);
        // If found, return the teaching with an HTTP 200 OK status.
        return new ResponseEntity<>(teaching, HttpStatus.OK);
    }

    /**
     * Endpoint to update an existing teaching's details.
     * This is the core functionality of the "Edit Teaching" use case.
     *
     * @param id The unique identifier of the teaching to update.
     * @param teachingDTO The {@link TeachingDTO} containing the updated details.
     *                    The {@code @Valid} annotation triggers validation defined in TeachingDTO.
     * @return A {@link ResponseEntity} containing the updated {@link Teaching} object if successful,
     *         or an appropriate error status (e.g., 400 Bad Request, 404 Not Found, 500 Internal Server Error)
     *         handled by {@link com.example.editteaching.exception.GlobalExceptionHandler}.
     */
    @PutMapping("/{id}") // Maps HTTP PUT requests to /api/teachings/{id}
    public ResponseEntity<Teaching> updateTeaching(@PathVariable String id,
                                                   @Valid @RequestBody TeachingDTO teachingDTO) {
        // Step 1: The @Valid annotation automatically performs data validity checks
        // based on annotations in TeachingDTO. If validation fails, a MethodArgumentNotValidException
        // is thrown and caught by GlobalExceptionHandler, activating the "Errodati" use case.

        // Step 2: Delegate the update operation to the service layer.
        // The service layer will handle business logic validation and persistence.
        Teaching updatedTeaching = teachingService.updateTeaching(id, teachingDTO);

        // Step 3: If the update is successful, return the updated teaching object
        // with an HTTP 200 OK status.
        // Postcondition: The user has changed a teaching.
        return new ResponseEntity<>(updatedTeaching, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a list of all teachings.
     * This supports the postcondition "View the list of updated teachings".
     *
     * @return A {@link ResponseEntity} containing a list of all {@link Teaching} objects
     *         with an HTTP 200 OK status.
     */
    @GetMapping // Maps HTTP GET requests to /api/teachings
    public ResponseEntity<List<Teaching>> getAllTeachings() {
        // Delegate the call to the service layer to fetch all teachings.
        List<Teaching> teachings = teachingService.getAllTeachings();
        // Return the list of teachings with an HTTP 200 OK status.
        return new ResponseEntity<>(teachings, HttpStatus.OK);
    }
}