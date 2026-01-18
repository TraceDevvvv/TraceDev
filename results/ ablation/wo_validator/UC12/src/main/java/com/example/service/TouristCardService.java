package com.example.service;

import com.example.dto.TouristCardDTO;
import com.example.model.CardStatus;
import com.example.model.Tourist;
import com.example.model.TouristCard;
import com.example.repository.TouristRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Service layer for Tourist Card operations.
 */
public class TouristCardService {
    private TouristRepository touristRepository;

    public TouristCardService(TouristRepository repository) {
        this.touristRepository = repository;
    }

    /**
     * Retrieves TouristCard data for a given tourist id.
     * This is a simulation: in reality, data would come from a database.
     * @param touristId the tourist id
     * @return TouristCard object
     */
    public TouristCard getTouristCardData(int touristId) {
        // Simulate fetching TouristCard from database or external service.
        // For demonstration, we create a dummy card.
        TouristCard card = new TouristCard();
        card.setTouristId(touristId);
        card.setCardNumber("CARD-" + touristId + "-2023");
        card.setIssueDate(new Date(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000)); // 30 days ago
        card.setExpirationDate(new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000)); // one year from now
        card.setStatus(CardStatus.ACTIVE);
        card.setSelectedDestinations(Arrays.asList("Paris", "London", "Rome"));
        return card;
    }

    /**
     * Converts a TouristCard entity to a TouristCardDTO as per sequence diagram.
     * @param touristCard the TouristCard entity
     * @return TouristCardDTO
     */
    public TouristCardDTO convertToDTO(TouristCard touristCard) {
        TouristCardDTO dto = new TouristCardDTO();
        dto.setTouristId(touristCard.getTouristId());
        // Fetch tourist name from repository
        Tourist tourist = touristRepository.findById(touristCard.getTouristId()).orElse(null);
        if (tourist != null) {
            dto.setTouristName(tourist.getFullName());
        } else {
            dto.setTouristName("Unknown");
        }
        dto.setCardNumber(touristCard.getCardNumber());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dto.setIssueDate(sdf.format(touristCard.getIssueDate()));
        dto.setExpirationDate(sdf.format(touristCard.getExpirationDate()));
        dto.setStatus(touristCard.getStatus().toString());
        dto.setSelectedDestinations(touristCard.getSelectedDestinations());
        return dto;
    }
}