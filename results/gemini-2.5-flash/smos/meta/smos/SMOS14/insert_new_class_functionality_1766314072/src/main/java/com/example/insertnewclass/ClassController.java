package com.example.insertnewclass;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for managing class-related operations.
 * This controller handles incoming HTTP requests, delegates business logic to {@link ClassService},
 * and returns appropriate HTTP responses.
 * It is responsible for the "InsertNewClass" functionality.
 */
@RestController // Marks this class as a REST controller, handling incoming web requests.
@RequestMapping("/api/classes") // Base path for all endpoints in this controller.
public class ClassController {

    private final ClassService classService;

    /**
     * Constructs a ClassController with the given ClassService.
     * Spring's dependency injection automatically provides the ClassService instance.
     *
     * @param classService The service layer component for class operations.
     */
    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    /**
     * Handles the HTTP POST request to create a new class.
     * This endpoint corresponds to the "InsertNewClass" use case.
     *
     * Preconditions:
     * - User is logged in as an administrator (authentication/authorization handled by security layer, not shown here).
     * - User has viewed classes and clicked "New Class" (frontend interaction).
     *
     * Events Sequence:
     * 1. User fills out the form (represented by {@link ClassCreateRequest}).
     * 2. User clicks "Save" button (triggers this POST request).
     * 3. System (this controller) receives the request.
     * 4. System performs data validation (via @Valid and ClassService).
     * 5. System inserts the new class into the archive.
     *
     * Postconditions:
     * - A new class is successfully created and archived, and a success response is returned.
     * - If data is invalid, an error response is returned (activating "Errodati" use case).
     *
     * @param request The {@link ClassCreateRequest} object containing the class details (name, address, academic year).
     *                The {@code @Valid} annotation triggers automatic validation based on constraints defined in ClassCreateRequest.
     * @return A {@link ResponseEntity} containing the {@link ClassResponse} of the newly created class
     *         with HTTP status 201 (Created) on success, or an error response on failure.
     */
    @PostMapping
    public ResponseEntity<ClassResponse> createClass(@Valid @RequestBody ClassCreateRequest request) {
        // Delegate the creation of the class to the ClassService.
        // The service handles business logic, further validation, and persistence.
        ClassResponse newClass = classService.createClass(request);

        // Return a 201 Created status with the details of the newly created class.
        return ResponseEntity.status(HttpStatus.CREATED).body(newClass);
    }
}