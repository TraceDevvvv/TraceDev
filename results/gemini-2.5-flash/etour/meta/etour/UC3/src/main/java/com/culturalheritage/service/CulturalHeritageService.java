package com.culturalheritage.service;

import com.culturalheritage.model.CulturalHeritageObject;
import com.culturalheritage.repository.CulturalHeritageRepository;
import com.culturalheritage.exception.CulturalHeritageNotFoundException;
import com.culturalheritage.exception.InvalidCulturalHeritageDataException;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing cultural heritage objects, encapsulating business logic.
 * This class orchestrates operations between the controller and the repository,
 * applying validation rules before persistence.
 */
public class CulturalHeritageService {

    private final CulturalHeritageRepository repository;
    private final CulturalHeritageValidator validator;

    /**
     * Constructs a new CulturalHeritageService with the given repository and validator.
     *
     * @param repository The data repository for cultural heritage objects.
     * @param validator The validator for cultural heritage object data.
     */
    public CulturalHeritageService(CulturalHeritageRepository repository, CulturalHeritageValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    /**
     * Retrieves a cultural heritage object by its ID.
     *
     * @param id The unique identifier of the cultural heritage object.
     * @return The CulturalHeritageObject if found.
     * @throws CulturalHeritageNotFoundException if no object with the given ID exists.
     */
    public CulturalHeritageObject getCulturalHeritageObject(String id) {
        Optional<CulturalHeritageObject> object = repository.findById(id);
        return object.orElseThrow(() -> new CulturalHeritageNotFoundException("Cultural Heritage Object with ID " + id + " not found."));
    }

    /**
     * Updates an existing cultural heritage object.
     * The object's data is validated before being saved to the repository.
     *
     * @param object The CulturalHeritageObject with updated data.
     * @throws InvalidCulturalHeritageDataException if the provided object data is invalid.
     */
    public void updateCulturalHeritageObject(CulturalHeritageObject object) {
        // Validate the object before saving
        validator.validate(object);
        // Check if the object exists before updating
        if (repository.findById(object.getId()).isEmpty()) {
            throw new CulturalHeritageNotFoundException("Cannot update: Cultural Heritage Object with ID " + object.getId() + " does not exist.");
        }
        repository.save(object);
    }

    /**
     * Retrieves all cultural heritage objects.
     *
     * @return A list of all CulturalHeritageObject entities.
     */
    public List<CulturalHeritageObject> getAllCulturalHeritageObjects() {
        return repository.findAll();
    }
}