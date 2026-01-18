package com.example.turista.application;

import com.example.turista.data.ITuristaRepository;
import com.example.turista.data.TuristaDataAccessException;
import com.example.turista.domain.Turista;
import com.example.turista.domain.TuristaDTO;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Service layer for Turista-related operations.
 * This class orchestrates data retrieval from the repository and transforms
 * domain entities into DTOs suitable for the presentation layer.
 */
public class TuristaService {
    // - turistaRepository : ITuristaRepository
    private ITuristaRepository turistaRepository;

    /**
     * Constructor for TuristaService.
     * @param turistaRepository The repository for accessing Turista data.
     */
    public TuristaService(ITuristaRepository turistaRepository) {
        this.turistaRepository = turistaRepository;
    }

    /**
     * Retrieves the card details for a specific Turista, formatted as a DTO.
     * This is the core method to retrieve data (R8).
     *
     * @param turistaId The ID of the turista whose details are requested.
     * @return A TuristaDTO containing the formatted details.
     * @throws TuristaNotFoundException if the turista cannot be found or data access fails.
     * note right of TuristaService::getTuristaCardDetails: Core method to retrieve data (R8)
     */
    public TuristaDTO getTuristaCardDetails(String turistaId) throws TuristaNotFoundException {
        System.out.println(" Service: getTuristaCardDetails called for Turista ID: " + turistaId);
        Turista turista;
        try {
            // Service -> Repository: findById(turistaId : String)
            turista = turistaRepository.findById(turistaId);
        } catch (TuristaDataAccessException e) {
            // Repository --> Service: Exception: TuristaDataAccessException
            System.err.println(" Service: Data access error for Turista ID " + turistaId + ": " + e.getMessage());
            // Re-throw as TuristaNotFoundException for the application layer.
            throw new TuristaNotFoundException("Could not retrieve Turista details: " + e.getMessage(), e);
        }

        // Service -> Service: mapTuristaToDTO(turista : Turista)
        TuristaDTO turistaDTO = mapTuristaToDTO(turista);
        System.out.println(" Service: Successfully mapped Turista to DTO for ID: " + turistaId);
        return turistaDTO;
    }

    /**
     * Maps a Turista domain object to a TuristaDTO.
     * This private helper method handles the transformation logic, formatting fields
     * for presentation.
     *
     * @param turista The Turista domain object to map.
     * @return A new TuristaDTO instance.
     * note TuristaDTO structure and data mapping within TuristaService ensure accurate detail display (R11)
     */
    private TuristaDTO mapTuristaToDTO(Turista turista) {
        System.out.println(" Service: Mapping Turista object to TuristaDTO...");
        // id : String
        String id = turista.getId();
        // fullName : String (e.g., "John Doe")
        String fullName = turista.getFirstName() + " " + turista.getLastName();
        // mainContact : String (e.g., "Email: john.doe@example.com, Phone: +1-555-123-4567")
        String mainContact = "Email: " + turista.getContactEmail() + ", Phone: " + turista.getContactPhone();

        // briefDetails : String (e.g., "Nationality: American, DOB: 1990-01-15")
        // Handle Date formatting
        String dobString = (turista.getDob() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(turista.getDob()) : "N/A";
        String briefDetails = "Nationality: " + (Objects.requireNonNullElse(turista.getNationality(), "N/A")) +
                              ", DOB: " + dobString;

        return new TuristaDTO(id, fullName, mainContact, briefDetails);
    }
}