package com.etour.service;

import com.etour.dto.TouristDTO;
import com.etour.dto.TouristCardDTO;
import com.etour.dto.ErrorMessageDTO;
import com.etour.model.Tourist;
import com.etour.repository.TouristRepository;
import com.etour.dto.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class that encapsulates business logic for tourist operations.
 * It acts as a mediator between the controller and the repository.
 */
public class TouristService {

    private TouristRepository touristRepository;

    /**
     * Constructor for dependency injection.
     * @param touristRepository the repository to be used.
     */
    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    /**
     * Retrieves all tourists from the repository and converts them to DTOs.
     * @return list of TouristDTO objects.
     */
    public List<TouristDTO> getAllTourists() {
        List<Tourist> tourists = touristRepository.findAll();
        return tourists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a tourist by ID and returns a Response object that may contain
     * the tourist or an error.
     * @param id the tourist ID.
     * @return Response containing either a Tourist or an ErrorMessageDTO.
     */
    public Response<Tourist> getTouristById(String id) {
        try {
            Tourist tourist = touristRepository.findById(id);
            if (tourist != null) {
                return new Response<>(tourist, null);
            } else {
                return new Response<>(null, new ErrorMessageDTO(404, "Tourist not found.", new java.util.Date()));
            }
        } catch (Exception e) {
            // Handle any exception (e.g., connection error) as per sequence diagram.
            return new Response<>(null, new ErrorMessageDTO(500, "Error retrieving tourist: " + e.getMessage(), new java.util.Date()));
        }
    }

    /**
     * Converts a Tourist entity to a TouristDTO.
     * @param tourist the Tourist entity.
     * @return corresponding TouristDTO.
     */
    public TouristDTO convertToDTO(Tourist tourist) {
        TouristDTO dto = new TouristDTO();
        dto.setId(tourist.getId());
        dto.setName(tourist.getName());
        dto.setEmail(tourist.getEmail());
        return dto;
    }

    /**
     * Converts a Tourist entity to a TouristCardDTO (includes additional details).
     * @param tourist the Tourist entity.
     * @return corresponding TouristCardDTO.
     */
    public TouristCardDTO convertToCardDTO(Tourist tourist) {
        TouristCardDTO card = new TouristCardDTO();
        card.setId(tourist.getId());
        card.setName(tourist.getName());
        card.setEmail(tourist.getEmail());
        card.setDetails(tourist.getOtherDetails());
        return card;
    }
}