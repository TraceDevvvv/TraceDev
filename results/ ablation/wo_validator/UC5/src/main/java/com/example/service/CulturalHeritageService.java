package com.example.service;

import com.example.dto.CulturalHeritageDetailsDTO;
import com.example.dto.CulturalHeritageSearchDTO;
import com.example.model.HeritageSearchCriteria;
import com.example.repository.CulturalHeritageRepository;
import com.example.model.CulturalHeritageEntity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for cultural heritage operations.
 * Contains business logic and coordinates data access.
 */
public class CulturalHeritageService {

    private CulturalHeritageRepository heritageRepository;

    /**
     * Constructor for dependency injection.
     * @param heritageRepository the repository for data access
     */
    public CulturalHeritageService(CulturalHeritageRepository heritageRepository) {
        this.heritageRepository = heritageRepository;
    }

    /**
     * Retrieves detailed information of a cultural heritage by its ID.
     * @param id the unique identifier of the cultural heritage
     * @return CulturalHeritageDetailsDTO with detailed information
     */
    public CulturalHeritageDetailsDTO getHeritageDetails(String id) {
        try {
            CulturalHeritageEntity entity = heritageRepository.findById(id);
            return mapToDetailsDTO(entity);
        } catch (Exception e) {
            // Handle exception (e.g., connection error) as per sequence diagram
            System.err.println("Error retrieving heritage details: " + e.getMessage());
            // Return an empty DTO to indicate error (could also throw a custom exception)
            return new CulturalHeritageDetailsDTO(); // empty DTO
        }
    }

    /**
     * Searches for cultural heritage items based on criteria.
     * @param criteria the search criteria (keyword, period, location)
     * @return List of CulturalHeritageSearchDTO representing search results
     */
    public List<CulturalHeritageSearchDTO> searchHeritage(HeritageSearchCriteria criteria) {
        try {
            List<CulturalHeritageEntity> entities = heritageRepository.findAllByCriteria(criteria);
            return entities.stream()
                    .map(this::mapToSearchDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error during heritage search: " + e.getMessage());
            // Return empty list on error
            return List.of();
        }
    }

    /**
     * Maps a CulturalHeritageEntity to a CulturalHeritageDetailsDTO.
     * @param entity the entity to map
     * @return the mapped DTO
     */
    protected CulturalHeritageDetailsDTO mapToDetailsDTO(CulturalHeritageEntity entity) {
        if (entity == null) {
            return null;
        }
        CulturalHeritageDetailsDTO dto = new CulturalHeritageDetailsDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        dto.setPeriod(entity.getPeriod());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    /**
     * Maps a CulturalHeritageEntity to a CulturalHeritageSearchDTO.
     * @param entity the entity to map
     * @return the mapped DTO
     */
    protected CulturalHeritageSearchDTO mapToSearchDTO(CulturalHeritageEntity entity) {
        if (entity == null) {
            return null;
        }
        CulturalHeritageSearchDTO dto = new CulturalHeritageSearchDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        // For brief info, we use a substring of description or a default.
        String brief = entity.getDescription();
        if (brief != null && brief.length() > 50) {
            brief = brief.substring(0, 50) + "...";
        }
        dto.setBriefInfo(brief);
        return dto;
    }
}