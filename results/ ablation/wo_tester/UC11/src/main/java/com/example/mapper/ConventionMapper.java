package com.example.mapper;

import com.example.dto.PointOfRestDTO;
import com.example.dto.ConventionHistoryDTO;
import com.example.model.PointOfRest;
import com.example.model.ConventionHistory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class to convert between entities and DTOs.
 */
public class ConventionMapper {
    public ConventionHistoryDTO toDTO(ConventionHistory conventionHistory) {
        return new ConventionHistoryDTO(
            conventionHistory.getConventionId(),
            conventionHistory.getConventionDate(),
            conventionHistory.getDetails()
        );
    }

    public List<ConventionHistoryDTO> toDTOList(List<ConventionHistory> conventionHistories) {
        return conventionHistories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a PointOfRestDTO to a PointOfRest entity.
     * Since DTO does not contain isDesignated, we default to true for demonstration.
     * In a real scenario, this might involve a lookup or additional logic.
     */
    public PointOfRest toEntity(PointOfRestDTO pointOfRestDTO) {
        return new PointOfRest(
            pointOfRestDTO.getId(),
            pointOfRestDTO.getName(),
            pointOfRestDTO.getLocation(),
            true // default to designated; could be enhanced
        );
    }
}