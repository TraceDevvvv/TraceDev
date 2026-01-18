package com.example.controller;

import com.example.dto.TouristCardDTO;
import com.example.dto.TouristDTO;
import com.example.model.Tourist;
import com.example.service.TouristCardService;
import com.example.service.TouristService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for handling Tourist related requests.
 */
public class TouristController {
    private TouristService touristService;
    private TouristCardService touristCardService;

    public TouristController() {
        // In a real application, dependencies would be injected (e.g., via Spring).
        // Here we instantiate them directly for simplicity.
        com.example.repository.TouristRepository repository = new com.example.repository.TouristRepository();
        this.touristService = new TouristService(repository);
        this.touristCardService = new TouristCardService(repository);
    }

    /**
     * Searches tourists based on criteria.
     * @param criteria search string
     * @return list of TouristDTO
     */
    public List<TouristDTO> searchTourists(String criteria) {
        List<Tourist> tourists = touristService.findTouristsByCriteria(criteria);
        return tourists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets all tourists.
     * @return list of TouristDTO
     */
    public List<TouristDTO> getTouristList() {
        List<Tourist> tourists = touristService.findAllTourists();
        return tourists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets a tourist by id.
     * @param touristId the tourist id
     * @return TouristDTO or null if not found
     */
    public TouristDTO getTouristById(int touristId) {
        Tourist tourist = touristService.findTouristById(touristId);
        if (tourist != null) {
            return convertToDTO(tourist);
        }
        return null;
    }

    /**
     * Displays Tourist Card for a given tourist id.
     * Implements the sequence diagram flow:
     * 1. Upload tourist data (with possible connection interruption)
     * 2. Retrieve TouristCard data and convert to DTO
     * @param touristId the tourist id
     * @return TouristCardDTO or null if tourist not found
     */
    public TouristCardDTO displayTouristCard(int touristId) {
        // Upload tourist data step
        boolean uploadSuccess = touristService.uploadTouristData(touristId);
        // According to sequence diagram, we continue regardless of success?
        // For simplicity, we only log the result (in a real app we might handle error).
        if (!uploadSuccess) {
            System.out.println("Warning: Upload to ETOUR server failed for tourist id " + touristId);
        }

        // Display Tourist Card step
        com.example.model.TouristCard card = touristCardService.getTouristCardData(touristId);
        if (card == null) {
            return null;
        }
        return touristCardService.convertToDTO(card);
    }

    /**
     * Helper to convert Tourist entity to TouristDTO.
     */
    private TouristDTO convertToDTO(Tourist tourist) {
        TouristDTO dto = new TouristDTO();
        dto.setId(tourist.getId());
        dto.setFullName(tourist.getFullName());
        dto.setEmail(tourist.getEmail());
        dto.setPhoneNumber(tourist.getPhoneNumber());
        dto.setRegistrationDate(tourist.getRegistrationDate());
        return dto;
    }
}