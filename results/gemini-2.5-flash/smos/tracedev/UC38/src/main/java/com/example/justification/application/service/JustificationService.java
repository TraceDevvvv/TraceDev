
package com.example.justification.application.service;

import com.example.justification.application.dto.JustificationDetailsDto;
import com.example.justification.application.dto.ModifyJustificationRequestDto;
import com.example.justification.application.port.in.EliminateJustificationUseCase;
import com.example.justification.application.port.in.ModifyJustificationUseCase;
import com.example.justification.application.port.in.ViewJustificationDetailsUseCase;
import com.example.justification.application.port.out.IJustificationRepository;
import com.example.justification.domain.model.Justification;

/**
 * Application service for managing justifications.
 * This class implements all three justification use cases, ensuring accurate and up-to-date data handling (R10).
 */
public class JustificationService implements ViewJustificationDetailsUseCase, ModifyJustificationUseCase {

    private final IJustificationRepository justificationRepository;

    /**
     * Constructs a new JustificationService with the given repository.
     *
     * @param repo The repository for accessing and storing Justification entities.
     */
    public JustificationService(IJustificationRepository repo) {
        this.justificationRepository = repo;
    }

    /**
     * Implements the {@link ViewJustificationDetailsUseCase}.
     * Retrieves the details of a justification and maps it to a DTO.
     *
     * @param id The unique identifier of the justification.
     * @return A {@link JustificationDetailsDto} containing the details.
     * @throws IllegalArgumentException if the justification with the given ID is not found.
     */
    @Override
    public JustificationDetailsDto execute(String id) {
        System.out.println("[UseCase] -> [RepoInterface]: findById(id=" + id + ")");
        Justification justification = justificationRepository.findById(id);

        if (justification == null) {
            // In a real application, this might be a custom NotFoundException
            throw new IllegalArgumentException("Justification with ID " + id + " not found.");
        }
        System.out.println("[RepoInterface] --> [UseCase]: justification (Justification entity for " + id + ")");

        // Maps Justification entity to JustificationDetailsDto. (m11)
        System.out.println("[UseCase] Note: Maps Justification entity to JustificationDetailsDto.");
        JustificationDetailsDto dto = mapToDto(justification);
        System.out.println("[UseCase] --> [Controller]: justificationDetailsDto (JustificationDetailsDto object)");
        return dto;
    }

    /**
     * Implements the {@link ModifyJustificationUseCase}.
     * Finds a justification, updates its details and status, and saves the changes.
     *
     * @param id      The unique identifier of the justification to modify.
     * @param details A {@link ModifyJustificationRequestDto} containing the new details and status.
     * @throws IllegalArgumentException if the justification with the given ID is not found.
     */
    @Override
    public void execute(String id, ModifyJustificationRequestDto details) {
        System.out.println("[UseCase] -> [RepoInterface]: findById(id=" + id + ")");
        Justification justification = justificationRepository.findById(id);

        if (justification == null) {
            throw new IllegalArgumentException("Justification with ID " + id + " not found for modification.");
        }
        System.out.println("[RepoInterface] --> [UseCase]: justification (Justification entity for " + id + ")");

        justification.setDetails(details.details);
        justification.setStatus(details.status);

        System.out.println("[UseCase] -> [RepoInterface]: save(justification)");
        justificationRepository.save(justification);
        System.out.println("[RepoInterface] --> [UseCase]: Save successful");
    }

    /**
     * Implements the {@link EliminateJustificationUseCase}.
     * Deletes a justification by its unique identifier.
     *
     * @param id The unique identifier of the justification to eliminate.
     */
    // The original design has a method signature clash:
    // ViewJustificationDetailsUseCase.execute(String id) returns JustificationDetailsDto
    // EliminateJustificationUseCase.execute(String id) returns void
    // A single class cannot implement two interfaces with methods of the same signature but incompatible return types.
    // To resolve this, one of the conflicting interfaces is removed from the 'implements' clause.
    // The method's functionality remains, but it no longer explicitly implements the 'execute' method from EliminateJustificationUseCase.
    // If strict adherence to all three interfaces in one class is required, the interface definitions themselves would need modification.
    public void eliminate(String id) { // Renamed to avoid method signature clash
        System.out.println("[UseCase] -> [RepoInterface]: delete(id=" + id + ")");
        justificationRepository.delete(id);
        System.out.println("[RepoInterface] --> [UseCase]: Delete successful");
    }

    /**
     * Private helper method to map a {@link Justification} entity to a {@link JustificationDetailsDto}.
     *
     * @param justification The Justification entity to map.
     * @return A new {@link JustificationDetailsDto} instance.
     */
    private JustificationDetailsDto mapToDto(Justification justification) {
        return new JustificationDetailsDto(
                justification.getId(),
                justification.getDetails(),
                justification.getStatus(),
                justification.getAbsenceId()
        );
    }
}
