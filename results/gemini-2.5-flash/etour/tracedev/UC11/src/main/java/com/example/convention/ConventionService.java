package com.example.convention;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Service layer class responsible for business logic related to conventions.
 * Fulfils REQ1: Goal of viewing convention history.
 */
public class ConventionService {
    private final ConventionRepository conventionRepository;

    /**
     * Constructs a ConventionService with a dependency on ConventionRepository.
     *
     * @param conventionRepository The repository used to access convention data.
     */
    public ConventionService(ConventionRepository conventionRepository) {
        this.conventionRepository = conventionRepository;
    }

    /**
     * Retrieves the history of conventions for a given Point of Rest ID.
     * Orchestrates REQ5: System uploads data on conventions.
     * This method acts as an application layer entry point for convention history.
     *
     * @param pointOfRestId The ID of the Point of Rest.
     * @return A list of {@link ConventionDTO}s representing the convention history.
     * @throws EtourConnectionException if there's a problem connecting to the underlying data source.
     */
    public List<ConventionDTO> getConventionHistory(String pointOfRestId) {
        System.out.println("Service: Getting convention history for pointOfRestId: " + pointOfRestId);
        List<Convention> conventions;
        try {
            // Sequence diagram step: Service -> Repository: findByPointOfRestId
            conventions = conventionRepository.findByPointOfRestId(pointOfRestId);
            System.out.println("Service: Received " + conventions.size() + " conventions from repository.");
        } catch (EtourConnectionException e) {
            // Sequence diagram step: Service --x Controller: Error: ETOUR connection interrupted (m12)
            System.err.println("Service: Failed to retrieve conventions from repository. " + e.getMessage());
            throw e; // Re-throw the exception to propagate the error
        }

        // Sequence diagram step: Service -> Service: transform List<Convention> to List<ConventionDTO> (m14)
        return transformListConventionToListConventionDTO(conventions);
    }

    /**
     * Transforms a list of {@link Convention} domain objects into a list of {@link ConventionDTO}s.
     * This method ensures domain entities are mapped to DTOs for efficient and accurate
     * transmission to the presentation layer (REQ8: accuracy).
     *
     * This method implements the sequence diagram message m14: "transform List<Convention> to List<ConventionDTO>".
     * @param conventions The list of domain Convention objects.
     * @return A list of ConventionDTOs.
     */
    private List<ConventionDTO> transformListConventionToListConventionDTO(List<Convention> conventions) {
        System.out.println("Service: Transforming " + conventions.size() + " Convention objects to ConventionDTOs.");
        return conventions.stream()
                .map(convention -> new ConventionDTO(
                        convention.getId(),
                        convention.getName(),
                        convention.getDate(),
                        convention.getDescription()
                ))
                .collect(Collectors.toList());
    }
}