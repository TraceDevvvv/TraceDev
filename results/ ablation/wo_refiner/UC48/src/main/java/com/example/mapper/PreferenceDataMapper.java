package com.example.mapper;

import com.example.model.GenericPreference;
import com.example.dto.PreferenceDTO;
import java.util.Map;

/**
 * Maps between Preference DTO and Entity.
 */
public class PreferenceDataMapper {
    public PreferenceDataMapper() {
    }

    // Missing method from class diagram: mapToEntity
    public GenericPreference mapToEntity(PreferenceDTO dto) {
        if (dto == null) {
            return null;
        }
        Map<String, String> attributes = dto.getAttributes();
        GenericPreference entity = new GenericPreference(
            dto.getId(),
            dto.getTouristId(),
            attributes.get("language"),
            attributes.get("theme"),
            attributes.get("notificationSettings")
        );
        return entity;
    }

    // Missing method from class diagram: mapToDTO
    public PreferenceDTO mapToDTO(GenericPreference entity) {
        if (entity == null) {
            return null;
        }
        Map<String, String> details = entity.getDetails();
        PreferenceDTO dto = new PreferenceDTO();
        dto.setId(entity.getPreferenceId());
        dto.setTouristId(entity.getTouristId());
        dto.setAttributes(details);
        return dto;
    }
}