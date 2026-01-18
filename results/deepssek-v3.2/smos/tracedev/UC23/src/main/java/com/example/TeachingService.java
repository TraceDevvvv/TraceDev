package com.example;

/**
 * Service class for teaching-related operations.
 */
public class TeachingService {
    private TeachingRepository repository;

    /**
     * Constructor for TeachingService.
     * @param repository the teaching repository
     */
    public TeachingService(TeachingRepository repository) {
        this.repository = repository;
    }

    /**
     * Inserts a teaching based on form data.
     * @param form the teaching form
     * @return the saved teaching
     * @throws RuntimeException if validation fails or connection error
     */
    public Teaching insertTeaching(TeachingForm form) {
        validateTeachingData(form);
        String name = form.getTeachingName();
        Teaching teaching = createTeaching(name);
        return repository.save(teaching);
    }

    /**
     * Creates a Teaching object from a name.
     * @param name the teaching name
     * @return a new Teaching object
     */
    public Teaching createTeaching(String name) {
        return new Teaching(name);
    }

    /**
     * Validates teaching form data.
     * @param form the teaching form
     * @throws IllegalArgumentException if data is invalid
     */
    void validateTeachingData(TeachingForm form) {
        if (form == null || !form.isFilled()) {
            throw new IllegalArgumentException("Teaching form data is invalid");
        }
    }
}