package com.atastaff.absencesystem.controller;

import com.atastaff.absencesystem.model.Class; // Explicitly import model.Class to avoid conflict with java.lang.Class
import com.atastaff.absencesystem.repository.ClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for handling class-related API requests.
 * Provides endpoints for retrieving class information.
 */
@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private static final Logger logger = LoggerFactory.getLogger(ClassController.class);

    private final ClassRepository classRepository;

    /**
     * Constructor for ClassController, injecting the ClassRepository.
     *
     * @param classRepository Repository for Class entities.
     */
    public ClassController(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    /**
     * Retrieves a list of all classes available in the system.
     *
     * @return A ResponseEntity containing a list of Class objects and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Class>> getClasses() {
        logger.info("Received request to get all classes.");
        List<Class> classes = classRepository.findAll();
        logger.debug("Found {} classes.", classes.size());
        return ResponseEntity.ok(classes);
    }

    /**
     * Retrieves a specific class by its unique identifier.
     *
     * @param classId The unique ID of the class to retrieve.
     * @return A ResponseEntity containing the Class object if found (HTTP status 200 OK),
     *         or HTTP status 404 (Not Found) if no class with the given ID exists.
     */
    @GetMapping("/{classId}")
    public ResponseEntity<Class> getClassById(@PathVariable String classId) {
        logger.info("Received request to get class by ID: {}", classId);
        Optional<Class> classOptional = classRepository.findById(classId);
        if (classOptional.isPresent()) {
            logger.debug("Found class with ID: {}", classId);
            return ResponseEntity.ok(classOptional.get());
        } else {
            logger.warn("Class with ID {} not found.", classId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}