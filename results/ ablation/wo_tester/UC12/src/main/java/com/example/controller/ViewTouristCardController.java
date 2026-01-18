package com.example.controller;

import com.example.dto.TouristDTO;
import com.example.exception.ConnectionException;
import com.example.model.Tourist;
import com.example.repository.TouristRepository;
import com.example.display.TouristCardDisplay;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for viewing tourist cards.
 */
public class ViewTouristCardController {
    private final TouristRepository touristRepository;

    public ViewTouristCardController(TouristRepository repository) {
        this.touristRepository = repository;
    }

    /**
     * Gets a list of tourists matching the search criteria.
     */
    public List<TouristDTO> getTouristList(String searchCriteria) {
        try {
            List<Tourist> tourists = touristRepository.findAll(searchCriteria);
            List<TouristDTO> dtos = new ArrayList<>();
            for (Tourist t : tourists) {
                dtos.add(new TouristDTO(t));
            }
            return dtos;
        } catch (ConnectionException e) {
            System.err.println("Connection error: " + e.getMessage());
            // Return empty list on error (could also throw a runtime exception)
            return new ArrayList<>();
        }
    }

    /**
     * Gets details of a specific tourist by ID.
     */
    public TouristDTO getTouristDetails(int touristId) {
        try {
            Optional<Tourist> tourist = touristRepository.findById(touristId);
            if (tourist.isPresent()) {
                // Requirement: Flow of Events: 5 - System uploads data to the selected account.
                // This is a placeholder for any persistence/update operation that might be needed.
                // In this simple implementation we just return the DTO.
                return new TouristDTO(tourist.get());
            } else {
                return null;
            }
        } catch (ConnectionException e) {
            System.err.println("Connection error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Displays the tourist card. Added to satisfy requirement Flow of Events: 4.
     */
    public void displayTouristCard(TouristDTO touristDTO) {
        // Delegate display to TouristCardDisplay component
        TouristCardDisplay display = new TouristCardDisplay();
        display.display(touristDTO);
    }

    /**
     * Converts a list of Tourist entities to a list of TouristDTO.
     * This method satisfies sequence diagram message m4: "convert to List<TouristDTO>"
     */
    public List<TouristDTO> convertToListTouristDTO(List<Tourist> tourists) {
        List<TouristDTO> dtos = new ArrayList<>();
        for (Tourist t : tourists) {
            dtos.add(new TouristDTO(t));
        }
        return dtos;
    }
}