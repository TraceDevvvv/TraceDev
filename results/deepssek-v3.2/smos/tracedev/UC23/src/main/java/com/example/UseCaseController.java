package com.example;

/**
 * Abstract base class for use case controllers.
 */
public abstract class UseCaseController {
    protected TeachingRepository repository;

    /**
     * Constructor for UseCaseController.
     * @param repository the teaching repository
     */
    public UseCaseController(TeachingRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case with a request object.
     * @param request the request object
     * @return a response object
     */
    public abstract Response execute(Object request);
}

/**
 * Base class for response objects.
 * Assumption: Added for generality.
 */
abstract class Response {
    // Common response properties if any
}