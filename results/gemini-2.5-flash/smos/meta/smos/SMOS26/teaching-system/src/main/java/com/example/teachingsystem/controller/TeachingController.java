package com.example.teachingsystem.controller;

import com.example.teachingsystem.exception.TeachingNotFoundException;
import com.example.teachingsystem.model.Teaching;
import com.example.teachingsystem.service.TeachingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Teaching entities.
 * Handles incoming HTTP requests related to teaching operations,
 * such as deleting a teaching and retrieving the list of teachings.
 */
@RestController // Marks this class as a REST controller, meaning it handles HTTP requests
@RequestMapping("/api/teachings") // Base path for all endpoints in this controller
public class TeachingController {

    private final TeachingService teachingService;

    /**
     * Constructor for TeachingController, injecting TeachingService.
     * Spring's dependency injection automatically provides an instance of TeachingService.
     *
     * @param teachingService The service layer for teaching-related business logic.
     */
    public TeachingController(TeachingService teachingService) {
        this.teachingService = teachingService;
    }

    /**
     * Handles DELETE requests to eliminate a teaching from the archive.
     *
     * @param id The unique identifier of the teaching to be deleted, extracted from the URL path.
     * @return A ResponseEntity containing the updated list of teachings and an HTTP status of 200 OK
     *         upon successful deletion, or an appropriate error status if the teaching is not found.
     */
    @DeleteMapping("/{id}") // Maps HTTP DELETE requests to /api/teachings/{id}
    public ResponseEntity<List<Teaching>> deleteTeaching(@PathVariable Long id) {
        // Delegate the deletion logic to the service layer.
        // The service will handle finding the teaching, deleting it,
        // and interacting with the SMOS server.
        teachingService.deleteTeaching(id);

        // After successful deletion, retrieve the updated list of teachings
        // to fulfill the "Displays the list of updated teachings" event.
        List<Teaching> updatedTeachings = teachingService.getAllTeachings();

        // Return the updated list with an HTTP 200 OK status.
        return ResponseEntity.ok(updatedTeachings);
    }

    /**
     * Handles GET requests to retrieve all teachings.
     * This endpoint can be used to display the initial list of teachings
     * or the updated list after a deletion.
     *
     * @return A ResponseEntity containing a list of all Teaching entities and an HTTP status of 200 OK.
     */
    @GetMapping // Maps HTTP GET requests to /api/teachings
    public ResponseEntity<List<Teaching>> getAllTeachings() {
        // Delegate the retrieval logic to the service layer.
        List<Teaching> teachings = teachingService.getAllTeachings();
        // Return the list of teachings with an HTTP 200 OK status.
        return ResponseEntity.ok(teachings);
    }

    /**
     * Exception handler for TeachingNotFoundException.
     * When a TeachingNotFoundException is thrown by the service layer,
     * this method catches it and returns an HTTP 404 Not Found response.
     *
     * @param ex The TeachingNotFoundException that was thrown.
     * @return A ResponseEntity with an HTTP 404 Not Found status and the exception message in the body.
     */
    @ExceptionHandler(TeachingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Sets the HTTP status code for this exception
    public ResponseEntity<String> handleTeachingNotFoundException(TeachingNotFoundException ex) {
        // Log the exception for debugging purposes (optional, but good practice)
        // LoggerFactory.getLogger(TeachingController.class).warn("Teaching not found: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}