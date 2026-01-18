package com.example.tourist;

/**
 * Implementation of TouristService.
 * Orchestrates business logic and transforms entity to DTO.
 */
public class TouristServiceImpl implements TouristService {
    private TouristRepository touristRepository;

    public TouristServiceImpl(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @Override
    public TouristDTO getTouristDetails(String id) {
        // Get tourist entity from repository
        Tourist tourist = touristRepository.findById(id);
        
        if (tourist == null) {
            return null;
        }
        
        // Convert entity to DTO as per sequence diagram m21
        return new TouristDTO(tourist);
    }
}