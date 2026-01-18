package com.example.mapper;

import com.example.model.Teaching;
import com.example.dto.TeachingDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to map between Teaching entity and TeachingDTO.
 */
public class TeachingMapper {
    /**
     * Converts a Teaching entity to a TeachingDTO.
     * @param teaching The entity to convert.
     * @return The corresponding DTO.
     */
    public static TeachingDTO toDTO(Teaching teaching) {
        if (teaching == null) {
            return null;
        }
        return new TeachingDTO(
            teaching.getId(),
            teaching.getTitle(),
            teaching.getDescription(),
            teaching.getStatus()
        );
    }

    /**
     * Converts a TeachingDTO to a Teaching entity.
     * @param dto The DTO to convert.
     * @return The corresponding entity.
     */
    public static Teaching toEntity(TeachingDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Teaching(
            dto.getId(),
            dto.getTitle(),
            dto.getDescription(),
            dto.getStatus()
        );
    }

    /**
     * Converts a list of Teaching entities to a list of TeachingDTOs.
     * @param teachings The list of entities.
     * @return The list of DTOs.
     */
    public static List<TeachingDTO> toDTOList(List<Teaching> teachings) {
        if (teachings == null) {
            return null;
        }
        return teachings.stream()
                .map(TeachingMapper::toDTO)
                .collect(Collectors.toList());
    }
}